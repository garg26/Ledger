package simplifii.framework.utility;

import android.os.Bundle;

import com.examples.ledger.Defaults;

import java.util.LinkedHashMap;
import java.util.Locale;

public interface AppConstants {

    String DEF_REGULAR_FONT = "Roboto-Regular.ttf";
    String APP_LINK = "https://drive.google.com/file/d/0B8wKJnD6sONHeXlUbm5pOTk4dGM/view?usp=sharing";
    LinkedHashMap<Integer, String> storeCategory = new LinkedHashMap<Integer, String>();
    String REGISTRATION_COMPLETE = "registrationComplete";
    int REQUESTCODE_GOOGLE_SIGHN_IN = 101;

    String USER_TUTION_CENTER = "tuition_centre";
    String USER_TUTOR = "tutor";
    String USER_STUDENT = "student";
    String X_ACCESS_TOKEN = "x-access-token";
    String TOKEN = "token";
    String USER_CLINIC = "Clinic";
    String USER_DOCTOR = "Doctor";
    int PLACE_PICKER_REQUEST = 1;

    interface FRAGMENT_TYPES {
        int PROFILE_LIST_FRAGMENT = 1;
        int CONTACT_CLINIC_DETAILS_FRAGMENT = 2;
        int TIMINGS_FRAGMENT = 3;
        int LOCATION_PHOTOS_FRAGMENT = 4;
        int DOCTORS_FRAGMENT = 5;
        int SPECIALIZATIONS_SERVICES_FRAGMENT = 6;
        int AWARD_ACCEREDATIONS_FRAGMENT = 7;
    }

    interface ASSETS_RESOURCES {
        String JSON_FOLDER = "jsons";
        String TUTOR_PROFILE_STRUCTURE = JSON_FOLDER + "/tutor_profile_structure.json";
        String TUITION_PROFILE_STRUCTURE = JSON_FOLDER + "/tuition_profile_structure.json";
        String ABOUT_TUTOR_JSON = JSON_FOLDER + "/about_me.json";
        String TAGLINE_EX_JSON = JSON_FOLDER + "/tagline.json";
        String TUTOR_DATA = JSON_FOLDER + "/tutor_data.json";
    }



    interface REQUEST_CODES {

        int GOOGLE_SIGHN_IN = 10;
        int REGISTER = 11;
        int UPDATE_PROFILE = 12;
        int INVITESTUDENT = 13;
        int CREATECLASS = 14;
        int MARK_ATTENDANCE = 15;
        int CREATE_INVOICE = 16;
        int EDIT_CONTENT = 17;
        int REQ_PICK_IMAGE = 18;
        int GET_FEED = 18;
        int EDIT_FEED = 19;
        int EDIT_SAVE_FEED = 20;
        int UPDATE_DATA = 21;
        int VIEW_ATTENDANCE = 22;
        int REQ_ABOUT_ME_EX = 23;
        int REQ_TAGLINE_EX = 24;
        int UPDATE_BOOKMARK_FEED = 25;
        int OPEN_SUBJECTS = 26;
        int OPEN_FOLDER = 27;
        int CREATE_CALENDAR_EVENT = 28;
    }

    public static interface VALIDATIONS {
        String EMPTY = "empty";
        String EMAIL = "email";
        String MOBILE = "mobile";
    }

    public static interface PARAMS {
        String LAT = "latitude";
        String LNG = "longitude";
    }


    public static interface ERROR_CODES {

        public static final int UNKNOWN_ERROR = 0;
        public static final int NO_INTERNET_ERROR = 1;
        public static final int NETWORK_SLOW_ERROR = 2;
        public static final int URL_INVALID = 3;
        public static final int DEVELOPMENT_ERROR = 4;

    }

    public static interface PAGE_URL {

        String BASEURL = "https://api.backendless.com/"+ Defaults.APPLICATION_ID+"/" + Defaults.API_KEY + "/";
        String BASE_WEb_LINK = "https://faqulty.club/";
        String PHOTO_URL = "https://faqulty.club/mobile";
        String BASE_HTTPS_URL = "https://faqulty.club/mobile";


        String FEED_LINK = BASE_WEb_LINK + "forum/";
        String TUTORSURL = BASEURL + "api/tutors/%s/";
        String TUTION_CENTRE_URL = BASEURL + "api/tution-centre/";
        String SIGNUP = BASEURL + "CheckLogin/UserSignup";
        String OTP_VERIFY = BASEURL + "verify-phone";
        String LOGIN = BASEURL + "CheckLogin/CheckLogIn";
        String GET_CITIES = BASEURL + "api/cities";
        String GET_LOCALITIES = BASEURL + "api/localities";
        String GET_USER = BASEURL + "api/users/";
        String UPDATE_USER = BASEURL + "api/users/";
        String UPDATE_TUTION_DETAILS = BASEURL + "api/users/";
        String GET_SUBJECTS = BASEURL + "api/subjects";
        String GET_DEGREE_SUBJECTS = BASEURL + "api/degree-subjects";
        String GET_CLASSES = BASEURL + "api/classes";
        String GET_BOARDS = BASEURL + "api/boards";
        String GET_MODES = BASEURL + "api/modes";
        String GET_LANGUAGES = BASEURL + "api/languages";
        String UPDATE_SOCIAL_DETAILS = BASEURL + "api/";
        String GET_DEGREE = BASEURL + "api/degrees";
        String GET_INSTITUTES = BASEURL + "api/institutes";
        String FORGOT_PASSWORD = BASEURL + "forgot-password";
        String RESET_PASSWORD = BASEURL + "reset-password";
        String UPLOAD_IMAGE = BASEURL + "file-ingest";
        String UPLOAD_FILE = BASEURL + "file-ingest/";
        String GET_IMAGE = BASEURL;
        String GET_SCHOOLS = BASEURL + "api/schools";
        String SOCIAL_LOGIN = BASEURL + "pre-login";
        String FACULTY_URL = TUTION_CENTRE_URL + "%s" + "/faculty";
        String CREATEGROUP = BASEURL + "api/groups";
        String INVITESTUDENTS = BASEURL + "api/students";
        String UPDATE_STUDENT_INVITE = "students/%s";
        String GETTUTORSTUDENT = BASEURL + "api/tutors/%s" + "/students";
        String GET_STUDENT_DATA = BASEURL + "api/tutors/%s" + "/students/%s";
        String GET_GROUP_DATA = BASEURL + "api/tutors/%s" + "/groups/%s";
        String GET_STUDENT_TRANSACTION = BASEURL + "api/tutors/%s" + "/students/%s/transactions";
        String GET_STUDENT_TRANSACTION_REMINDER = BASEURL + "api/tutors/%s" + "/students/%s/transactions/reminders";
        String GETTUTORGROUP = BASEURL + "api/tutors/%s" + "/groups";
        String GETATTENDANCE = BASEURL + "api/students/%s/tutors/%s/attendances";
        String GET_GROUP_ATTENDANCE = BASEURL + "api/groups/%s/attendances";
        String ATTENDANCE = BASEURL + "api/attendances";
        String GROUP_URL = BASEURL + "api/groups/";
        String FETCH_GROUP = GROUP_URL + "%s";
        String ASSIGNMENT_GET = BASEURL + "api/classes?sort=sort_order";
        String GET_ASSIGNMENT_SUBJECTS = BASEURL + "api/assignments/subjects";
        String GET_SUBJECT_CHAPTERS = BASEURL + "api/assignments/chapters";
        String INVOICE_URL = GET_STUDENT_DATA + "/invoice-data";
        String INVOICE_URL_GROUP = GET_GROUP_DATA + "/invoice-data";
        String CREATE_INVOICE = GET_STUDENT_DATA + "/payments";
        String CREATE_NEW_INVOICE = GET_STUDENT_DATA + "/invoices";
        String CREATE_NEW_TRANSACTION = GET_STUDENT_DATA + "/transactions";
        String PAYMENT_URL = GET_STUDENT_DATA + "/payments";
        String REMINDER_URL = GET_STUDENT_DATA + "/transactions/reminders";
        String TUTOR_CONTENT = BASEURL + "api/tutors/%s" + "/contents";
        String INVOICE_EDIT_URL = BASEURL + "api/invoices/";
        String BOOKMARK_GET = "bookmarks";
        String CREATE_BOOKMARK_FOLDER = "bookmark-folders";
        String GET_COMMENT = "feed/%s/comments";
        String POST_COMMENT = "comments?type=feed";
        String CREATE_BOOKMARK = "bookmarks";
        String FETCH_FEED_DATA = "my-feed?offset=%s&count=%s";
        String POST_FEED_URL = "feed";
        String DELETE_FEED = "feed/";
        String LIKE_URL = "feed/%s/likes";
        String LIKE_FEED_POST = BASEURL + "api/likes?type=feed";
        String ASSIGNMENTS = BASEURL + "api/assignments";
        String BOOKMARKS_FOLDER = "bookmark-folders/%s";
        String RESENDOTP = BASEURL + "resend-otp";
        String DELETE_TRANSACTION = BASEURL + "api/transactions/";
        String DELETE_GROUP = BASEURL + "api/tutors/%s/groups";
        String DELETE_GROUP_STUDENT = BASEURL + "api/groups/%s/students/%s";
        String SEND_REVIEW = BASEURL + "api/reviews/invites";
        String GET_HASH_TAGS = BASEURL + "api/tags";
        String FETCH_BOOKMARK_FEED = "feed/";
        String GET_NOTIFICATIONS = BASEURL + "api/tutors/%S/notifications";
        String GET_RECENT_ASSIGNMENTS = TUTORSURL + "assignments";
        // questions/answers/fullset
        String DOWNLOAD_ASSIGNMENT_LINK = BASEURL + "api/assignments/%s/document-links";
        String UPDATE_CONTENT = "contents/%s";
        String FEED_VIEWS = "feed/views";
        String DELETE_FOLDER = BASEURL + "api/tutors/%s/bookmark-folders/%s";
        String FEATCH_ALL_PAMPHLETS = BASEURL + "api/pamphlets/templates";
        String FEATCH_USER_PAMPGLETS = BASEURL + "api/tutors/%s/pamphlets";
        String CREATE_PAMPHLETS = BASEURL + "api/tutors/%s/pamphlets";
        String GENERATE_TEMPLATE = BASEURL + "api/tutors/%s/pamphlets/%s/generate";
        String CERATE_LOGO = BASEURL + "api/tutors/%s/brandings";
        String GET_LOGO_DATA = BASEURL + "api/tutors/%s/brandings";
        String DELETE_PAMPHLET = BASEURL + "api/tutors/%s/pamphlets/%s";
        String FEED_LIKES = "feed/%s/likes";
        String ASK_QUERY = BASEURL + "api/contact";
        String CHAT_URL = "http://161.202.30.26:4000/";
        //        String CHAT_URL = BASEURL;
        String FCM_UPDATE_TOKEN = BASEURL + "api/push-token";
        String DELETE_STUDENT_FROM_GROUP = BASEURL + "api/groups/%s/students/%s";
        String GET_PROFILE_COMMENTS = BASE_WEb_LINK + "api/tutorinfo/";
        String EMAIL_SHARE = BASEURL + "api/share";
        //        String CHAT_URL = BASEURL;
//        api/students/548/tutors/99/attendances?offset=0&count=2
//

        String GET_SHOP_CATEOGRY = BASEURL + "data/shopCateogry";


        String USER_REGISTER = BASEURL + "users/register";
        String USER_LOGIN = BASEURL + "users/login";
        String USER_TOKEN = BASEURL + "users/isvalidusertoken/";
        String FILE_UPLOAD = BASEURL + "files/userImage/user.png?overwrite=true";
        String USER_IMAGE = BASEURL + "files/userImage/";
        String ACCOUNT_INFO = BASEURL + "data/Accounts";
    }

    public static interface PREF_KEYS {

        String KEY_LOGIN = "IsUserLoggedIn";
        String KEY_USERNAME = "username";
        String KEY_EMAIL = "email";
        String KEY_PASSWORD = "password";
        String ACCESS_CODE = "access";
        String APP_LINK = "appLink";
        String USER_TOKEN = "user_token";
        String IS_LOGIN = "is_login";
        String IS_FIRST = "is_first";
        String PHONE_NO = "phoneno";
        String SOURCE = "source";
        String USER_TYPE = "userType";
        String USER_ID = "userId";
        String BASIC_PROFILE_REQUIRED = "isBasicProfileRequired";
        String IS_REFRESH_ASSIGNMENT_VIEWED = "isRefreshAssignment";
        String IS_UPDATE_TOKEN = "is_token_update";
        String FCM_TOKEN = "fcm_token";
        String KEY_PAMPHLETS = "pamphlet_data";
        String COMMENTS_COUNT = "commentsCount";
    }

    public static interface BUNDLE_KEYS {
        public static final String KEY_SERIALIZABLE_OBJECT = "KEY_SERIALIZABLE_OBJECT";
        public static final String FRAGMENT_TYPE = "FRAGMENT_TYPE";
        String EXTRA_BUNDLE = "bundle";
        String SOCIALSIGNUP = "isSocialSignUp";
        String KEY_ABOUT_TITLE = "aboutTitle";
        int ADDSTUDENTS = 1;
        String SELECTEDSTUDENTS = "selectedstudents";
        String STUDENT = "student";
        String KEY_STUDENT_ID = "keyStudentId";
        String KEY_IS_GROUP_STUDENT = "isGroupStudent";
        String ARRAY_STUDENT_ATTENDANCE = "arrayStudentsAttendance";
        java.lang.String GROUP_ID = "groupId";
        String CLASS = "class";
        String SUBJECT = "subject";
        String CHAPTERS = "chapters";
        String SELECTEDCONTACTS = "selectedContacts";
        java.lang.String IS_EDITABLE = "isEditable";
        java.lang.String IS_NOT_EDITABLE = "isNotEditable";
        String PENDING_AMOUNT = "pendingAmount";
        java.lang.String PDF_URL = "pdf";
        String KEY_URL = "url";
        String MIME_TYPE = "mimeType";
        String KEY_MESSAGE = "message";
        String UPDATE = "update";
        String POST_FEED = "postFeed";

        String USERTYPE = "userType";
        String PASSWORD = "password";
        String PHONE = "mobile";
        String SAVED_CONTACTS = "savedContacts";
        String FEED_ITEM = "feed msg";
        String EDIT_POSTFEED = "editPost";
        String FEEDDATA = "feedData";
        String EDIT_NEW_POSTFEED = "newPostFeed";
        String MODULE_ID = "moduleId";
        String ENTITY_ID = "entityId";
        String Q_ID = "Q_ID";
        String KEY_TITLE = "title";
        String KEY_SUBTITLE = "subtitle";
        java.lang.String FOLDER_NAME = "folderName";
        java.lang.String FOLDER_ID = "folderId";
        java.lang.String MODULE_TYPE = "moduleType";
        String ASSIGNMENTS = "assignments";
        String QUESTIONS = "questions";
        java.lang.String SESSION_DATE = "sessionDate";
        String KEY_EXAMPLE_TEXT = "exampleText";
        String KEY_FILE_NAME = "fileName";
        String ID = "id";
        String IF_POST = "ifPost";
        String STUDENT_DATA = "studentData";
        String FROM_DATE = "fromDate";
        String TO_DATE = "toDate";
        String FRAGMENT_MESSAGE = "fragmentMessage";
        String IMAGE_FRAGMENT_TITLE = "imageFragmentTitle";
        String PAMPHLET_DATA = "pamphlet_data";
        java.lang.String FEED_ID = "feedId";
        java.lang.String TYPE = "type";
        String META_DATA = "meta_data";
        String IS_QUE_ONLY = "is_question_only";
        String MESSAGE_TYPE = "message_type";
        String KEY_CALENDAR_EVENT = "calendarEvent";
    }


    public static interface VIEW_TYPE {
        int CARD_MY_TEAM = 0;
        int GET_IMAGE = 1;
        int GET_AUDIO = 2;
        int GET_PDF = 3;
        int GET_VIDEO = 4;
        int GET_HEADER = 5;
        int CONTENT = 6;
        String GET_FILE = "file";
        Integer BOOKMARK = 6;
        Integer FOLDER = 7;
        int FEED_DATA = 10;
        int FEED_CONTENT = 11;
        int GROUP = 12;
        int GROUP_STUDENT = 13;
        int STUDENT = 14;
        int CLASSES = 15;
        int RECENT_ASSIGNMENT = 16;
        int FEED_AUDIO = 17;
        int PDF_FILE = 18;
        int SHOW_MORE = 19;

        interface CHAT_TYPES {
            int TEXT_MESSAGE_RECEIVE = 100;
            int TEXT_MESSAGE_SEND = 101;
            int MEDIA_MESSAGE_SEND = 102;
            int MEDIA_MESSAGE_RECEIVE = 103;
            int BOOKMARK_MESSAGE_SEND = 104;
            int BOOKMARK_MESSAGE_RECEIVE = 105;
            int ASSIGNMENT_MESSAGE_SEND = 107;
            int ASSIGNMENT_MESSAGE_RECEIVE = 108;
            int CALENDAR_MESSAGE_SEND = 109;
            int CALENDAR_MESSAGE_RECEIVE = 110;
            int FEED_MESSAGE_SEND = 111;
            int FEED_MESSAGE_RECEIVE = 112;
        }
    }

    public static interface MEDIA_TYPES {
        String IMAGE = "img";
        String AUDIO = "audio";
        String VIDEO = "video";
    }

    interface USER_TYPES {
        String STUDENT = "STUDENT";
        String TUTOR = "TUTOR";
        String TCENTRE = "TCENTRE";
    }

    public interface TASKCODES {
        int GET_SHOP_CATEOGRY = 1;
        int USER_REGISTER = 2;
        int USER_LOGIN = 3;
        int USER_TOKEN = 4;
        int FILE_UPLOAD = 5;
        int USER_ACCOUNT = 6;
    }

    interface ProfileStructureType {
        int PERSONAL_DETAILS = 1;
        int LOCATION = 2;
        int TUTION_DETAILS = 3;
        int QUALIFICATION = 4;
        int SOCIAL_PROFILES = 5;
        int CONTENT = 6;
        int REVIEWS = 7;
        int BASIC_DETAILS = 8;
        int FACULTY_DETAILS = 9;
    }

    public interface IMAGE_CODE {
        int IMAGE = 21;
    }

    public interface FILE_TYPES {

        String IMAGE = "image";
        String AUDIO = "audio";
        String VIDEO = "video";
        String PDF = "pdf";
        String CALENDAR = "calendar";
    }

    public interface FILE_REQUEST_CODE {
        int IMAGE = 13;
        int AUDIO = 14;
        int VIDEO = 15;
        int PDF = 16;
    }

    interface META_TYPES {
        String TEXT = "TEXT";
        String FEED = "FEED";
        String FILE = "FILE";
        String ASSIGNMENT = "ASSIGNMENT";
        String CALENDAR = "CALENDAR";
    }

    interface ACTION_TYPE {
        int ACTION_EDIT_CONTENT = 1;
        int EDIT_BOTTOMSHEET = 2;
        int SHARE_BOTTOMSHEET = 3;
        int BOOKMARK = 4;
        int DELETE_FEED = 5;
        int LIKE_EVENT = 6;
        int EDIT_FEED = 7;
        int DELETE_FEED_CONTENT_ITEM = 8;
        int COMMENT_BOOKMARK = 9;
        int DOWNLOAD_FEED = 10;
        int GROUP_STUDENT_CLICKED = 11;
        int GROUP_CLICKED = 12;
        int GROUP_ATTENDANCE = 13;
        int GROUP_LONG_CLICK = 14;
        int GROUP_STUDENT_LONG_CLICKED = 15;
        int SHARE_FEED = 16;
        int RECENT_ASSIGNMENT_CLICKED = 17;
        int CLASS_CLICKED = 18;
        int SHARE_QUESTION_SET = 19;
        int SHARE_ANSWER_KEY = 20;
        int EMAIL = 21;
        int COPY_FEED_LINK = 22;
        int VIEW_MORE = 23;
        int CHAT = 24;
        int SHARE_FEED_ON_CHAT = 25;
    }

     interface ACCOUNT{
        String ACCOUNT = "Accounts";
        int ACCOUNT_ID = 1;
    }
     interface CREDITDEBIT{
        String CREDITDEBIT = "Creadit/Debit";
        int CREDITDEBIT_ID = 2;
    }
     interface HISTORY{
        String HISTORY = "History";
        int HISTORY_ID = 3;
    }
     interface SETTINGS{
        String SETTINGS = "Settings";
        int SETTINGS_ID = 4;
    }


}

