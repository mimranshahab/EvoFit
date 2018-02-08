package edu.aku.akuh_health_first.constatnts;

import edu.aku.akuh_health_first.fragments.LoginFragment;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebServiceConstants {

    String postmanLink = "https://www.getpostman.com/collections/1d39c488e283b95f6e23";

    public static String BASE_URL = "http://ahfapidev.aku.edu/api/";
    public static String PACS_VIEWER_URL = "https://pacsviewer.aku.edu/api/PACSViewer";
    public static String PACS_URL_DOWNLOAD = "https://pacsviewer.aku.edu/";


    public static final String PARAMS_REQUEST_METHOD = "RequestMethod";
    public static final String PARAMS_REQUEST_DATA = "RequestData";
    public static final String requestor = "aku.edu";
    public static final String temporaryToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ3d3cuYWt1LmVkdSIsInN1YiI6Im1ubUBtbm0uY29tIiwiaXNzIjoiTW5NIn0.PRbF6RNmjYm7ai8jQNShBmyDLlZfIWv2Z6V2KosJgjE";
    public static final String tempUserName = "mahrukh.mehmood";
    public static final String tempPacViews = "46015891";
    public static final String tempCardNumber = "0000-0000-0031";
    public static final String tempMRN = "291-32-60";
    public static final String tempMRN_LAB = "510-29-10";
    public static final String tempMRN_RADIOLOGY = "294-71-23";
    public static final String tempMRN_immunization = "269-14-57";
    public static final String temp_Specimen_Num = "53786623";
    public static final String tempPassword = "uL952ERAghddR2h+CedbuA==";
    /**
     * REQUEST METHODS NAMES
     */

    // UserManager
    public static final String METHOD_USER_GET_REGISTER_VM = "UserManager.GetRegisterVM";
    public static final String METHOD_USER_GET_USER = "UserManager.Login";
    public static final String METHOD_CARD_MEMBER = "UserManager.GetCardMembersDetail";
    public static final String METHOD_USER_UPLOAD_REQUEST_FILE = "UserManager.UploadRequestFile";
    public static final String METHOD_PACMANAGER = "PACSManager.GetPacs";
    public static final String METHOD_GET_RADIOLOGY_EXAMS = "RadiologyManager.GetRadiologyExams";
    public static final String METHOD_GET_RADIOLOGY_GET_EXAM_DETAIL = "RadiologyManager.GetExamDetail";
    public static final String METHOD_NEUROPHIOLOGY = "NeurophysiologyManager.GetNeurophysiologyExam";
    public static final String METHOD_NEUROPHIOLOGY_SHOW_REPORT= "NeurophysiologyManager.ShowReport";
    public static final String METHOD_CARDIO= "CardiopulmonaryManager.GetCardiopulmonaryExam";
    public static final String METHOD_CARDIO_SHOW_REPORT= "CardiopulmonaryManager.ShowReport";
    public static final String METHOD_CARDIO_SHOW_GRAPH= "CardiopulmonaryManager.ShowGraph";
    public static final String METHOD_CLINICAL_LAB= "LaboratoryManager.GetLabSpecimenList";
    public static final String METHOD_CLINICAL_LAB_DETAIL= "LaboratoryManager.GetLabSpecimenList";
    public static final String METHOD_CLINICAL_LAB_RESULT= "LaboratoryManager.GetLabSpecimenResults";
    public static final String METHOD_PATIENT_DETAIL_DS= "PatientManager.GetPatientDetail";
    public static final String METHOD_SHOW_REPORT_DS= "PatientManager.ShowReport";
    public static final String METHOD_VISIT_PATIENT_DS= "PatientManager.GetPatientVisit";
    public static final String METHOD_DISCHARGE_SUMMARY_LIST= "PatientManager.GetDischargeSummaryList";

    public static final String METHOD_IMMUNIZATION_VACCINE_SCHEDULE= "PharmacyManager.VaccineSchedule";
    public static final String METHOD_IMMUNIZATION_UPDATE_VACCINE= "PharmacyManager.UpdateVaccine";
    public static final String METHOD_IMMUNIZATION_ADD_VACCINE= "PharmacyManager.AddVaccine";
    public static final String METHOD_IMMUNIZATION_VACCINE_IDS= "PharmacyManager.GetVaccineIDs";


    /**
     * CONSTANTS
     */
    public static String DEVICE_TYPE = "android";
    public static String DEVICE_TOKEN = "abc123";
    public static String CURRENCY_TYPE = "AED";
    public static String COUNTRY_ID_UAE = "AE";

    public static int CITY_ID = 0;


    public static final String WS_KEY_LOGIN = "login";
    public static final String WS_KEY_REGISTER = "register";
    public static final String WS_KEY_EDIT_PROFILE = "user/update";
    public static final String WS_KEY_FORGOT_PASSWORD = "forgotpassword";
    public static final String WS_KEY_GET_TOKEN = "GetToken";
    public static final String WS_KEY_GET_REQUESTOR= "Requestor: aku.edu";
    public static final String WS_KEY_PACS_VIEWER = "PACSViewer";
    public static final String WS_KEY_IMAGE_URL = "wado?requestType=WADO&studyUID=1.2.392.200036.9125.2.353644173100.64863020020.362418&seriesUID=1.2.392.200036.9125.3.353644173100.64863020020.362419&objectUID=1.2.392.200036.9125.9.0.253360372.1325662243.606907748&contentType=image/jpeg";


    public static final String WS_KEY_STATIC_PAGE = "cms";
    public static final String WS_KEY_ADDRESSES = "address";
    public static final String WS_KEY_ADD_ADDRESS = "address/add";
    public static final String WS_KEY_DELETE_ADDRESS = "address/delete";
    public static final String WS_KEY_EDIT_ADDRESS = "address/edit";
    public static final String WS_KEY_NOTIFICATIONS_CLEAR = "notifications/clear";
    public static final String WS_KEY_NOTIFICATIONS_GET = "notifications/get";
    public static final String WS_KEY_FEEDBACK = "feedback/add";
    public static final String WS_KEY_SELECT_ADDRESS = "address/select";
    public static final String WS_KEY_GET_SELECTED_ADDRESS = "address/selected";
    public static final String WS_KEY_UPDATE_TOKEN = "updateToken";
    public static final String WS_KEY_SEND_CODE = "sendCode";
    public static final String WS_KEY_REPORT_AN_ISSUE = "issue/report";
    public static final String WS_KEY_RATING_BAR = "rate/shopping";
}
