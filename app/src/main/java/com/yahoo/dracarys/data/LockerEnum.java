package com.yahoo.dracarys.data;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jue on 2/16/15.
 */
public class LockerEnum {

    public static enum LockerState {
        LOANER_CLOSED_TO_SHARE(7,"LOANER_CLOSED_TO_SHARE"),
        LOANER_OPEN_TO_SHARE(0,"LOANER_OPEN_TO_SHARE"),
        LOANER_REQUEST_RECEIVED(1,"LOANER_REQUEST_RECEIVED"),
        LENDEE_LEND_REQUESTED(2,"LENDEE_LEND_REQUESTED"),
        LOANER_REQUEST_ACCEPTED(3,"LOANER_REQUEST_ACCEPTED"),
        LENDEE_LEND_ACCEPTED(4,"LENDEE_LEND_ACCEPTED"),
        LENDEE_LEND_REJECTED(5,"LENDEE_LEND_REJECTED"),
        LENDEE_LEND_RETURNED(6,"LENDEE_LEND_RETURNED");

        private final int status;
        private final String name;

        LockerState(final int status, final String name) {
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }
        public String getName() { return name; }
        private static  Map<Integer,LockerState> enumMap = new HashMap<Integer, LockerState>();
        static {
            for(LockerState s : EnumSet.allOf(LockerState.class))
                enumMap.put(s.getStatus(), s);
        }
        public static LockerState getStatus(int code){
            return enumMap.get(code);
        }
    }


    public static enum DisplayState {
        PRIVATE(0,"PRIVATE"),
        PUBLIC(1,"PUBLIC");

        private final int status;
        private final String name;

        DisplayState(final int status, final String name) {
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }
        public String getName() { return name; }
        private static  Map<Integer,DisplayState> enumMap = new HashMap<Integer, DisplayState>();
        static {
            for(DisplayState s : EnumSet.allOf(DisplayState.class))
                enumMap.put(s.getStatus(), s);
        }
        public static DisplayState getStatus(int code){
            return enumMap.get(code);
        }
    }

}
