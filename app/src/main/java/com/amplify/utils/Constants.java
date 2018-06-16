package com.amplify.utils;

public final class Constants {

    public static class Extras {
        public static final String EXTRA_DATE = "com.sp.loylapclover.date";
        public static final String EXTRA_SCANNED_BARCODE = Args.STATE_SCANNED_BARCODE;
        public static final String EXTRA_SHOULD_REQUEST_EMAIL = "com.sp.loylapclover.shouldRequestEmail";

        public static final String CLOVER_LINE_ITEM_ID = "com.clover.intent.extra.LINE_ITEM_ID";
        public static final String CLOVER_ITEM_ID = "com.clover.intent.extra.ITEM_ID";
        public static final String CLOVER_ORDER_ID = "com.clover.intent.extra.ORDER_ID";
        public static final String EXTRA_IS_FROM_CACHE_WIPE = "com.sp.loylapclover.isFromCacheWipe";
        public static final String EXTRA_STRICT_MODE = "com.sp.loylapclover.isStrictMode";

        public static final String CARD_READER_PACKAGE = "com.theobroma.cardreadermanager";
        public static final String HID_DEVICE_ATTACHED = "com.sp.loylapclover.usbDeviceAttached";
        public static final int HID_PRODUCT_ID = 21543;
        public static final int HID_VENDOR_ID = 1899;

        public static final int ACR122U_PRODUCT_ID = 8704;
        public static final int ACR122U_VENDOR_ID = 1839;

        public static final int MINI_DUMMY_PRODUCT_ID = 60416;
        public static final int MINI_DUMMY_VENDOR_ID = 1060;
        public static final int MINI_DUMMY_2_PRODUCT_ID = 296;
        public static final int MINI_DUMMY_2_VENDOR_ID = 1561;
        public static final int MINI_DUMMY_3_PRODUCT_ID = 2;
        public static final int MINI_DUMMY_3_VENDOR_ID = 10483;

        public static final int MINI_EXTERNAL_KEYPAD_VENDOR_ID = 10483;
        public static final int MINI_EXTERNAL_KEYPAD_PRODUCT_ID = 40961;

        public static final String EXTRA_URL_TO_SHOW = "com.sp.loylapclover.extraURLToShow";
        public static final String EXTRA_URL_POST_PARAMS = "com.sp.loylapclover.extraPostParams";
        public static final String EXTRA_SHOW_TOOLBAR = "com.sp.loylapclover.showToolbar";
        public static final String EXTRA_SHOW_TOOLBAR_HOME_BUTTON = "com.sp.loylapclover.showToolbarHomeButton";
        public static final String EXTRA_CUSTOMER_ID = "com.sp.loylapclover.customerID";

        public static final String EXTRA_IS_CUSTOMER_TERMINAL_TENDER_INTENT = "com.sp.loylapclover.isCustomerTenderIntent";
        public static final String EXTRA_IS_MERCHANT_TERMINAL_TENDER_INTENT = "com.sp.loylapclover.isMerchantTerminalIntent";
        public static final String EXTRA_IS_FROM_CUSTOMER_FACING_TERMINAL = "com.sp.loylapclover.isFromCustomerFacingTerminal";
        public static final String EXTRA_IS_LAUNCHER_INTENT = "com.sp.loylapclover.isLauncherIntent";
        public static final String EXTRA_TOOLBAR_TITLE = "com.sp.loylapclover.toolbarTitle";
        public static final String EXTRA_LOYALTY_ADD_ON_CLOSED_TRANSACTION = "com.sp.loylapclover.isLAddOnClosedTransaction";
        public static final String EXTRA_LINKING_A_CARD = "com.sp.loylapclover.isLinkingACard";

        public static final String EXTRA_IS_CUSTOMER_LOYALTY_COLLECT_INTENT = "com.sp.loylapclover.isCustomerLoyaltyCollectIntent";
        public static final String EXTRA_HAS_APPLIED_CAMPAIGN = "com.sp.loylapclover.hasAppliedCampaign";
        public static final String REWARD_AMOUNT_RESULT = "com.sp.loylapclover.rewardAmountResult";
    }

    public static class Args {
        public static final String TRANSACTION_TYPE = "com.sp.loylapclover.transactionType";
        public static final String STATE_SPINNER_SELECTION = "com.sp.loylapclover.spinnerSelection";
        public static final String STATE_SCANNED_CUSTOMER = "com.sp.loylapclover.scannedCustomer";
        public static final String STATE_SCANNED_BARCODE = "com.sp.loylapclover.scannedBarcode";
        public static final String STATE_IS_SCANNER_REGISTERED = "com.sp.loylapclover.isRegsitered";
        public static final String STATE_SCANNER_OPENED_IN_LANDSCAPE = "com.sp.loylapclover.openedInLandscape";
        public static final String REGISTRATION_SCHEME_TYPE = "com.sp.loylapclover.registrationSchemeType";
        public static final String CLOVER_EMAIL = "com.sp.loylapclover.cloverEmail";
        public static final String STATE_STORED_MERCHANT = "com.sp.loylapclover.theMerchant";
        public static final String STATE_STORED_ORDER = "com.sp.loylapclover.theOrder";
        public static final String STATE_IS_REFUND = "com.sp.loylapclover.isRefund";
        public static final String QUICK_AMOUNT = "com.sp.loylapclover.QuickAmount";
        public static final String IS_REGISTRATION_FLOW = "com.sp.loylapclover.IsRegistrationFlow";
        public static final String STATE_IS_STRICT_MODE = "com.sp.loylapclover.isStrictMode";
        public static final String FETCH_FULL_CACHE = "com.sp.loylapclover.fetchFullCache";
        public static final String CACHED_TRANSACTIONS_ORDER_ID = "com.sp.loylapclover.cacheTransactionsOrderID";
        public static final String STATE_IS_FROM_LAUNCHER = "com.sp.loylapclover.isFromLauncher";
        public static final String CONFIG_INV_CATEGORY_CHOICE = "com.sp.loylapclover.config_inv_category";
        public static final String LOYALTY_VARIABLE_REWARDS_JSON = "com.sp.loylapclover.config.l_variable_rewards";
        public static final String TIMEPICKER_HOUR = "com.sp.loylapclover.hour";
        public static final String TIMEPICKER_MINUTE = "com.sp.loylapclover.minute";
        public static final String DATE_TIME_START = "com.sp.loylapclover.dateTimeStart";
        public static final String DATE_TIME_END = "com.sp.loylapclover.dateTimeEnd";
        public static final String IS_CASHLESS_STAMP_INTERRUPT_REQUIRED = "com.sp.loylapclover.isCashlessStampInterruptRequired";
        public static final String IS_CASHLESS_STAMP_ADD_CHAIN_REQUIRED = "com.sp.loylapclover.isCashlessStampAddChainRequired";
        public static final String MANUAL_DIGITAL_GIFT = "com.sp.loylapclover.manual.digitalGift";
        public static final String MANUAL_DIGITAL_GIFT_AMOUNT = "com.sp.loylapclover.manual.digitalGiftAmount";
        public static final String STATE_IS_FROM_CLOSED_L_ADD = "com.sp.loylapclover.isFromClosedLoyaltyAdd";
        public static final String STATE_IS_FROM_CUSTOMER_L_ADD_INTENT = "com.sp.loylapclover.isFromCustomerLAddIntent";
        public static final String STATE_IS_FROM_LINK_A_CARD_INTENT = "com.sp.loylapclover.isFromLinkACardIntent";
        public static final String FIND_BY_NAME_STRING = "com.sp.loylapclover.findByNameString";
        public static final String FIND_BY_NAME_RESULTS = "com.sp.loylapclover.findByNameResults";
        public static final String FOUND_CUSTOMER = "com.sp.loylapclover.foundCustomer";
        public static final String IS_NESTED_FRAGMENT = "com.sp.loylapclover.isNestedFragment";
        public static final String LINK_A_CARD_ORIGINAL = "com.sp.loylapclover.linkACardOriginalID";
        public static final String LINK_A_CARD_ORIGINAL_REAL_ID = "com.sp.loylapclover.linkACardOriginalRealID";
        public static final String LINK_A_CARD_NEW = "com.sp.loylapclover.linkACardNewID";
    }

    public static class Tags {
    }

    public static class Intents {
        public static final String CLOVER_SCANNER_BROADCAST = "com.clover.stripes.BarcodeBroadcast";
        public static final String CLOVER_LINE_ITEM_BROADCAST = "com.clover.intent.action.LINE_ITEM_ADDED";
        public static final String CLOVER_PAYMENT_PROCESSED_BROADCAST = "com.clover.intent.action.PAYMENT_PROCESSED";
        public static final String CLOVER_SWIPE_CARD_BROADCAST = "com.clover.carddata.ACTION_CARD_DATA_READ";
        public static final String SWIPE_CARD_BROADCAST_FWD = "com.sp.loylapclover.SWIPE_CARD_BROADCAST";
        public static final String CLOVER_REFUND_BROADCAST = "com.sp.loylapclover.REFUND_BROADCAST";
        public static final String GIFT_REDEEM_TRANSACTION = "com.sp.vendor.GIFT_REDEEM_TRANSACTION";
        public static final String LAST_SCANNED_CUSTOMER = "com.sp.loylapclover.intent.lastScannedCustomer";
        public static final String CUSTOMER_LOYALTY_COLLECT_INTENT = "com.sp.loylapclover.isCustomerLoyaltyCollectIntent";
    }

    public static class RequestCodes{
        public static final int RC_TENDER_GIFT_REDEEM = 6000;
        public static final int RC_REFUND = 6001;
        public static final int RC_RETRO_LOYALTY_ADD = 6003;
    }

    public static class Prefs {
        public static final String LOGIN_IS_FIRST_LAUNCH = "com.sp.loylapclover.hasLaunchedBefore";
        public static final String LOGIN_NEEDS_REGISTRATION_ALERT_MAIL = "com.sp.loylapclover.needsInstallMail"; //NB This label is badly named for legacy reasons
        public static final String LOGIN_NEEDS_FIRST_INSTALL_MAIL = "com.sp.loylapclover.needsFirstTimeInstallMail";
        public static final String LAST_VERSION_UPDATED = "com.sp.loylapclover.lastVersionUpdated";
        public static final String MAIN_IS_ACTIVE = "com.sp.loylapclover.MAIN_IS_ACTIVE";
        public static final String API_KEY = "com.sp.loylapclover.API_KEY";
        public static final String LOGIN_TENDER_SUCCESSFULLY_CREATED = "com.sp.loylapclover.TENDER_CREATED";
        public static final String LOGIN_INVENTORY_SUCCESSFULLY_CREATED = "com.sp.loylapclover.INVENTORY_CREATED";
        public static final String REFRESH_TIMESTAMP = "com.sp.loylapclover.REFRESH_TIMESTAMP";
        public static final String MERCHANT_IDENTIFIER = "com.sp.loylapclover.MERCHANT_IDENTIFIER";
        public static final String MERCHANT_CURRENCY_SYMBOL = "com.sp.loylapclover.MERCHANT_CURRENCY_SYMBOL";
        public static final String CASHLESS_QUICK_REDEEM_ENABLED = "com.sp.vendor.QUICK_REDEEM_ENABLED";
        public static final String LAST_SCANNED_CUSTOMER = "com.sp.loylapclover.LAST_SCANNED_CUSTOMER";
        public static final String LOYALTY_QUICK_ADD_ENABLED = "com.sp.loylapclover.LOYALTY_QUICK_ADD_ENABLED";
        public static final String NUMERIC_KB_DEFAULT_ENABLED = "com.sp.loylapclover.NUMERIC_KB_DEFAULT";
        public static final String SETTING_IS_MULTI_DEVICE_MERCHANT = "com.sp.loylapclover.IS_MULTI_DEVICE_MERCHANT";
        public static final String RECEIPT_USE_FULL_NAME_ENABLED = "com.sp.loylapclover.RECEIPT_USE_FULL_NAME_ENABLED";
        public static final String RECEIPT_PRINT_SEPARATE_JOB_ENABLED = "com.sp.loylapclover.RECEIPT_PRINT_SEPARATE_JOB_ENABLED";
        public static final String CONTACTLESS_USES_RAW_WIEGAND_ATR = "com.sp.loylapclover.CONTACTLESS_USES_RAW_WIEGAND_ATR";
    }

    public class Answers {
        public static final String CONTENT_NAME_LOGIN_ACTIVITY = "Login activity";
        public static final String CONTENT_TYPE_ACTIVITY = "Activity";
        public static final String MESSAGE = "Message";
        public static final String ATTRIBUTE_INITIAL_ATTEMPT = "Inital Attempt";
        public static final String ATTRIBUTE_ATTEMPTED = "Attempted";
        public static final String ATTRIBUTE_SUCCESS = "Success";
        public static final String ATTRIBUTE_FAILURE = "Failure";
        public static final String ATTRIBUTE_FAILURE_WILL_RESCHEDULE = "Failure, Will Reschedule";
        public static final String ATTRIBUTE_CANCELLED = "Cancelled";
        public static final String ATTRIBUTE_EMAIL = "Email";
        public static final String ATTRIBUTE_REASON = "Reason";
        public static final String ATTRIBUTE_FAILURE_EXCEPTION = "Exception";
        public static final String ATTRIBUTE_GROUP_ID = "Group ID";
        public static final String ATTRIBUTE_GROUP_NAME = "Group Name";
        public static final String ATTRIBUTE_NAME = "Name";
        public static final String ATTRIBUTE_ID = "ID";
        public static final String ATTRIBUTE_LOCALE = "Locale";
        public static final String ATTRIBUTE_LOYALTY_TYPE = "Loyalty Type";
        public static final String CONTENT_NAME_HOME_FRAGMENT = "HomeFragment";
        public static final String CONTENT_TYPE_FRAGMENT = "Fragment";
        public static final String EVENT_MANUAL_ENTRY_CLICK = "Manual entry click";
        public static final String ATTRIBUTE_QR = "QR";
        public static final String EVENT_OPEN_SCANNER_CLICK = "Open scanner click";
        public static final String EVENT_SCAN_SUCCESS = "Successful scan";
        public static final String EVENT_SWIPE_SUCCESS = "Successful swipe";
        public static final String EVENT_SCAN_INVALID = "Invalid scan";
        public static final String CONTENT_NAME_UPFRONT_ACTIVITY = "Upfront activity";

        public static final String ITEM_UPFRONT_PURCHASE = "Upfront";
        public static final String ITEM_CASH_ADDITION_PURCHASE = "Cash addition";
        public static final String ITEM_CASH_REDEMPTION_PURCHASE = "Cash redemption";
        public static final String ITEM_STAMP_ADDITION_PURCHASE = "Stamp addition";
        public static final String ITEM_STAMP_REDEMPTION_PURCHASE = "Stamp redemption";
        public static final String ITEM_STAMP_CASH_FOR_STAMP_PURCHASE = "Cash for stamps redemption";
        public static final String ITEM_GIFT_ADDITION_PURCHASE = "Gift addition";
        public static final String ITEM_GIFT_REDEMPTION_PURCHASE = "Gift redemption";
        public static final String ITEM_REFUND_PURCHASE = "Refund";

        public static final String CONTENT_NAME_AR_CASH_FRAGMENT = "AddRedeemCashFragment";
        public static final String CONTENT_NAME_AR_STAMP_FRAGMENT = "AddRedeemStampFragment";
        public static final String ATTRIBUTE_CUSTOMER = "Customer";
        public static final String EVENT_NOTE_SAVED = "Note saved";
        public static final String ATTRIBUTE_NOTE = "Note";
        public static final String CONTENT_NAME_RECENTS_FRAGMENT = "RecentsFragment";
        public static final String CONTENT_NAME_ANALYTICS_FRAGMENT = "AnalyticsFragment";
        public static final String EVENT_CUSTOMER_FETCH = "Customer fetched";
        public static final String CONTENT_NAME_SCHEDULE_FRAGMENT = "ScheduleDemoFragment";
        public static final String EVENT_SCHEDULE_DEMO = "Schedule demo";
        public static final String ATTRIBUTE_PHONE = "Phone Number";
        public static final String ATTRIBUTE_BUSINESS_NAME = "Business Name";
        public static final String EVENT_FORGOT_PASSWORD = "Forgot Password";
        public static final String ATTRIBUTE_MERCHANT_ID = "Merchant ID";
        public static final String ATTRIBUTE_MERCHANT_NAME = "Merchant Name";
        public static final String ATTRIBUTE_CUSTOMER_ID = "Customer ID";
        public static final String ATTRIBUTE_CUSTOMER_NAME = "Customer Name";
        public static final String ATTRIBUTE_CUSTOMER_BALANCE = "Customer Balance (before)";
        public static final String ATTRIBUTE_LENGTH_BEFORE_COMPLETION = "Length before completion";
        public static final String EVENT_OPEN_FAQS_CLICK = "Open faqs click";
        public static final String EVENT_OPEN_CONFIGURE_LOYALTY_CLICK = "Open configure Loyalty products click";
        public static final String EVENT_OPEN_MERCHANT_HISTORY = "Open merchant history";
        public static final String CONTENT_NAME_CUSTOMER_FACING_FRAGMENT = "CustomerFacingFragment";
        public static final String CONTENT_NAME_TRANSACTION_ACTIVITY = "TransactionActivity";
        public static final String EVENT_TRANSACTION = "Performing LL transaction";
        public static final String ATTRIBUTE_FAILURE_FATAL_EXCEPTION = "Fatal exception";
        public static final String EVENT_STAMP_INTERRUPT_POPUP = "Cashless stamp interrupt popup";
        public static final String EVENT_STAMP_INTERRUPT_ASK_LATER = "Ask later";
        public static final String EVENT_STAMP_INTERRUPT_CONFIRM = "Confirm";
        public static final String EVENT_STAMP_INTERRUPT_POPUP_DISPLAYED = "Created";
        public static final String ATTRIBUTE_RETRYING = "Retry";
        public static final String ATTRIBUTE_ALL_TRANSACTIONS_FINISHED = "All transactions finished";
        public static final String ATTRIBUTE_TAG = "Tag";
        public static final String EVENT_GET_CLOVER_ORDER = "Getting Clover order";
        public static final String ATTRIBUTE_FINISH = "Finished activity";
        public static final String ATTRIBUTE_NEXT_TRANSACTION = "Next transaction found";
        public static final String ATTRIBUTE_ATTEMPTED_POST_GIFT_VALIDATION = "Attempt after no valid gifts were found";
        public static final String EVENT_CLOVER_GIFT_MISSING = "Clover gift missing";
        public static final String EVENT_RETRY_TRANSACTION = "Retry list launched";
        public static final String ATTRIBUTE_RETRY_LAUNCHED = "Retry launched";
        public static final String EVENT_BOOT_RECEIVED = "Device Booted";
        public static final String EVENT_LOGS_POSTED = "Logs Posted";
        public static final String ATTRIBUTE_REGISTRATION = "Registered";
        public static final String ATTRIBUTE_FIRST_LAUNCH = "First Launch";
        public static final String ATTRIBUTE_DEMO_SCHEDULED = "Demo Scheduled";
        public static final String EVENT_ONBOARDING = "Onboarding";
        public static final String EVENT_BACKGROUNDED_RETRY = "Backgrounded retry";
    }

    public class Logs {
        public static final String ACTION_ACTIVITY_CREATE = "activity created";
        public static final String CATEGORY_TRANSACTION = "Transaction";
        public static final String CATEGORY_CUSTOMER_DETAIL = "Customer Detail Display";
        public static final String ACTION_FIRST_TRANSACTION = "1st cached transaction found";
        public static final String ACTION_NEXT_TRANSACTION = "next cached transaction found";
        public static final String ACTION_GETTING_CLOVER_ORDER = "getting clover order";
        public static final String ACTION_LAUNCHING_LL_TRANSACTION_ACTIVITY = "launching LL transaction activity";
        public static final String ACTION_PERFORMING_LL_TRANSACTION = "performing LL transaction";
        public static final String ACTION_TRANSACTION_COMPLETE = "LL transaction complete";
        public static final String ACTION_ALL_TRANSACTIONS_COMPLETE = "all transactions complete!";
        public static final String ACTION_CLOVER_ORDER_RETRIEVED = "clover order retrieved";
        public static final String ACTION_CONTAINS_VALIDATED_GIFT = "order contains valid gift item";
        public static final String ACTION_NO_MORE_TRANSACTIONS = "all transactions finished";
        public static final String ACTION_NOT_CONTAINS_VALIDATED_GIFT = "order DOES NOT contain a valid gift item";
        public static final String ACTION_TRANSACTION_REMOVED = "transaction removed, no longer valid";
        public static final String ACTION_CLOVER_ORDER_RETRIEVAL_FAILED = "clover order retrieval failed";
        public static final String ACTION_TRANSACTION_FAILED = "transaction failed";
        public static final String ACTION_WONT_RETRY = "retry limit reached";
        public static final String ACTION_PRE_TRANSACTION = "pre-transaction";
        public static final String ACTION_MARKING_LL_TRANSACTION_ATTEMPTED = "marking LL transaction as attempted";
        public static final String ACTION_TRANSACTION_FAILED_LIMIT_REACHED = "transactions failed, retry limit reached";
        public static final String ACTION_TRANSACTION_CANNOT_RETRY = "transaction failed, could not load for retry";
        public static final String ACTION_MONTHLY_RANK = "monthly rank is being retrieved by a gift app";
		public static final String LABEL_SQL_CORRUPT_EXCEPTION = "An SQL corruption has occurred";
        public static final String ACTION_BACKSTACK_POP = "can't pop backstack, already executing transactions";
        public static final String CATEGORY_PLATFORM = "Platform issue";
        public static final String ACTION_BACKSTACK_POP_CONCURRENT_MOD = "can't pop backstack, fragments have changed";
        public static final String LABEL_SQL_EXCEPTION = "SQL Exception";
        public static final String ACTION_POSTING_TRANSACTION = "can't post trasaction to db";
        public static final String LABEL_GIFT_AMOUNT = "gift amount";
        public static final String ACTION_GIFT_REDEEM = "Gift redeem invalid amount";
        public static final String LABEL_JSON_EXCEPTION = "Json exception on cashback rewards";
        public static final String ACTION_PARSING_CASHBACK_REWARDS = "parsing cashback rewards";
        public static final String ACTION_GETTING_BUSINESS_DETAILS = "getting business details";
        public static final String CATEGORY_BUSINESS_DETAILS = "Business details issue";
        public static final String CATEGORY_HID_READER = "HID reader issue";
        public static final String ACTION_READING_FROM_HID = "reading from hid reader";
        public static final String ACTION_CONNECTING_TO_HID_CARD = "connecting to card";
        public static final String ACTION_GETTING_CLOVER_TOKEN = "Getting token";
        public static final String LABEL_MERCHANT_ID = "merchant id";
        public static final String ACTION_GETTING_INVENTORY = "Getting inventory";
        public static final String ACTION_SENDING_PUSH_NOTE = "Sending push notification";
        public static final String ACTION_GETTING_INVENTORY_NAME_TAG = "Getting inventory by name and tag";
        public static final String ACTION_GETTING_REWARD_TAGS = "Getting reward tags";
        public static final String ACTION_PERFORMING_SET_RESULT = "Performing setResult";
        public static final String ACTION_RUN_COMMAND_WAITING_READER_CONNECT = "cmd waiting reader connect";
        public static final String ACTION_RUN_COMMAND_READER_CONNECTED = "cmd reader connected";
        public static final String ACTION_RUN_COMMAND_WAITING_CARD_INSERT = "cmd waiting card insert";
        public static final String ACTION_RUN_COMMAND_CARD_INSERTED = "cmd card inserted";
        public static final String ACTION_RUN_COMMAND_WAITING_CARD_REMOVE = "cmd waiting card remove";
        public static final String ACTION_RUN_COMMAND_CARD_REMOVED = "cmd card removed";
        public static final String ACTION_RUN_COMMAND_BEFORE = "cmd before";
        public static final String ACTION_RUN_COMMAND_AFTER = "cmd after";
        public static final String ACTION_RUN_COMMAND_ERROR = "cmd error";
        public static final String ACTION_HID_LAUNCHING_THREAD = "Launching hid thread";
        public static final String ACTION_HID_INTERRUPTED_EXCEPTION = "Hid interruption";
        public static final String ACTION_HID_TRANSMISSION_INTERRUPTED = "Hid transmission interruption";
        public static final String ACTION_HID_LOOP_INTERRUPTED_DISCONNECT = "Hid loop disconnect interruption";
        public static final String ACTION_HID_LOOP_INTERRUPTED = "Hid loop interruption";
        public static final String ACTION_HID_OPENING_READER = "Hid opening reader";
        public static final String ACTION_HID_EXCEPTION = "Hid exception";
        public static final String ACTION_PERFORMING_BACKGROUNDED_LL_TRANSACTION = "performing background transaction";
    }
}
