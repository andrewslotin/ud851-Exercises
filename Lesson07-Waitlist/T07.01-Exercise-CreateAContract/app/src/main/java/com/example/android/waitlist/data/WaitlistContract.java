package com.example.android.waitlist.data;

import android.provider.BaseColumns;

public class WaitlistContract {

    public static final class WaitlistEntry implements BaseColumns {
        public final static String TABLE_NAME = "waitlist";
        public final static String COLUMN_GUEST_NAME = "guestName";
        public final static String COLUMN_PARTY_SIZE = "partySize";
        public final static String COLUMN_TIMESTAMP = "timestamp";
    }
}
