package edu.project3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class StatManager {

    private StatManager() {

    }

    public static Map<String, Integer> countResources(List<NginxLog> nginxLogList) {
        return nginxLogList.stream()
            .collect(Collectors.groupingBy(
                NginxLog::getResource,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    public static Map<Integer, Integer> countResponses(List<NginxLog> nginxLogList) {
        return nginxLogList.stream()
            .collect(Collectors.groupingBy(
                NginxLog::getResponseCode,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    public static int calculateAverageResponseSize(List<NginxLog> nginxLogList) {
        return nginxLogList.stream()
            .collect(Collectors.averagingInt(NginxLog::getResponseSize)).intValue();
    }

    public static Map<Integer, Integer> countRequestsByTimeOfDay(List<NginxLog> nginxLogList) {
        return nginxLogList.stream()
            .collect(Collectors.groupingBy(
                nginxLog -> nginxLog.getDateTime().getHour(),
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    public static int countUniqueIPAddresses(List<NginxLog> nginxLogList) {
        return nginxLogList.stream()
            .map(NginxLog::getIpAddress)
            .distinct()
            .collect(Collectors.collectingAndThen(Collectors.counting(), Long::intValue));
    }

}
