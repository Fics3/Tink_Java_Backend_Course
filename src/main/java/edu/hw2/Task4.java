package edu.hw2;

public class Task4 {
    public record CallingInfo(String className, String methodName) {
        public static CallingInfo callingInfo() {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

            String className = stackTraceElements[2].getClassName();
            String methodName = stackTraceElements[2].getMethodName();
            // 0 stackTrace of Thread
            // 1 callingInfo (this method)
            // 2 method that called callingInfo;

            return new CallingInfo(className, methodName);
        }
    }

}
