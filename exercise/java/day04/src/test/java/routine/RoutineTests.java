package routine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class RoutineTests {

    private EmailService emailService;
    private ScheduleService scheduleService;
    private ReindeerFeeder reindeerFeeder;

    private Routine routine;

    @Nested
    class Given_mocks {

        @BeforeEach
        void setUp() {
            emailService = mock(EmailService.class);
            scheduleService = mock(ScheduleService.class);
            reindeerFeeder = mock(ReindeerFeeder.class);
        }

        @Test
        void startRoutineWithMockito() {
            routine = new Routine(emailService, scheduleService, reindeerFeeder);
            routine.start();

            InOrder inOrder = Mockito.inOrder(scheduleService, reindeerFeeder, emailService);
            inOrder.verify(scheduleService).todaySchedule();
            inOrder.verify(scheduleService).organizeMyDay(any());
            inOrder.verify(reindeerFeeder).feedReindeers();
            inOrder.verify(emailService).readNewEmails();
            inOrder.verify(scheduleService).continueDay();
        }
    }

    @Nested
    class Given_manual_test_doubles {

        private boolean isEmailServiceReadNewEmailsCalled;
        private boolean isScheduleServiceTodayScheduleCalled;
        private boolean isScheduleServiceOrganizeMyDayCalled;
        private boolean isScheduleServiceContinueDayCalled;
        private boolean isReindeerFeederFeedReindeersCalled;

        @BeforeEach
        void setUp() {
            emailService = () -> isEmailServiceReadNewEmailsCalled = true;
            scheduleService = new ScheduleService() {
                @Override
                public Schedule todaySchedule() {
                    isScheduleServiceTodayScheduleCalled = true;
                    return new Schedule();
                }

                @Override
                public void organizeMyDay(Schedule schedule) {
                    isScheduleServiceOrganizeMyDayCalled = true;
                }

                @Override
                public void continueDay() {
                    isScheduleServiceContinueDayCalled = true;
                }
            };
            reindeerFeeder = () -> isReindeerFeederFeedReindeersCalled = true;
        }

        @Test
        void startRoutineWithManualTestDoubles() {
            routine = new Routine(emailService, scheduleService, reindeerFeeder);
            routine.start();

            assertThat(isScheduleServiceTodayScheduleCalled).isTrue();
            assertThat(isScheduleServiceOrganizeMyDayCalled).isTrue();
            assertThat(isReindeerFeederFeedReindeersCalled).isTrue();
            assertThat(isEmailServiceReadNewEmailsCalled).isTrue();
            assertThat(isScheduleServiceContinueDayCalled).isTrue();
        }
    }
}