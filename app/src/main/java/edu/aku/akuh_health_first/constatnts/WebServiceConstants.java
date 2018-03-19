package edu.aku.akuh_health_first.constatnts;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebServiceConstants {


    private static Map<String, String> headers;

    public static Map<String, String> getHeaders() {
        if (headers == null) {
            headers = new HashMap<>();
            headers.put("_token", temporaryToken);
        }
        return headers;
    }

    public static void setHeaders(Map<String, String> headers) {
        WebServiceConstants.headers = headers;
    }

    String postmanLink = "https://www.getpostman.com/collections/1d39c488e283b95f6e23";

    public static String BASE_URL = "http://ahfapidev.aku.edu/api/";
    public static String PACS_VIEWER_URL = "https://pacsviewer.aku.edu/api/PACSViewer/";
    public static String PACS_URL_DOWNLOAD = "https://pacsviewer.aku.edu/";
    public static String PACS_URL = "https://pacsviewer.aku.edu/api/";
    public static String PAYMENT_GATEWAY_URL = "https://testsecureacceptance.cybersource.com/pay/";
//    public static String PAYMENT_GATEWAY_URL = "https://testsecureacceptance.cybersource.com/token/create/";

    public static final String WS_KEY_GET_TOKEN = "GetToken";
    public static final String WS_KEY_GET_REQUESTOR = "Requestor: aku.edu";

    public static final String PARAMS_REQUEST_METHOD = "RequestMethod";
    public static final String PARAMS_REQUEST_DATA = "RequestData";
    public static final String requestor = "aku.edu";
    public static final String temporaryToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ3d3cuYWt1LmVkdSIsInN1YiI6Im1ubUBtbm0uY29tIiwiaXNzIjoiTW5NIn0.PRbF6RNmjYm7ai8jQNShBmyDLlZfIWv2Z6V2KosJgjE";
    public static final String tempUserName = "mahrukh.mehmood";
    public static final String tempPacViews = "46015891";
    public static final String tempCardNumber = "0000-0000-0031";
    public static final String tempMRN_Neuro = "291-32-60";
    public static final String tempMRN = "510-29-10";
    public static final String tempMRN_ENDOSCOPY = "100-08-60";
    public static final String tempMRN_RADIOLOGY = "294-71-23";
    public static final String tempMRN_RADIOLOGY1 = "015-94-53";
    public static final String tempMRN_immunization = "269-14-57";
    public static final String temp_Specimen_Num = "56070141";
    public static final String tempPassword = "user123";
    //    public static final String temp_Specimen_Num = "47556226";
//    public static final String tempMRN_Cardio = "275-02-02";
    public static final String tempMRN_Cardio = "289-49-15";
//    public static final String tempMRN_Cardio = "200-47-97";

    /**
     * REQUEST METHODS NAMES
     */

    // UserManager
    public static final String METHOD_USER_GET_REGISTER_VM = "UserManager.GetRegisterVM";
    public static final String METHOD_USER_GET_USER = "UserManager.Login";
    public static final String METHOD_CARD_MEMBER = "UserManager.GetRegisteredCardAndMembers";
    public static final String METHOD_USER_UPLOAD_REQUEST_FILE = "UserManager.UploadRequestFile";
    public static final String METHOD_USER_GET_USER_IMAGE = "UserManager.GetUserImage";


    public static final String METHOD_GET_RADIOLOGY_EXAMS = "RadiologyManager.GetRadiologyExams";
    public static final String METHOD_GET_RADIOLOGY_GET_EXAM_DETAIL = "RadiologyManager.GetExamDetail";


    public static final String METHOD_NEUROPHIOLOGY = "NeurophysiologyManager.GetNeurophysiologyExam";
    public static final String METHOD_NEUROPHIOLOGY_SHOW_REPORT = "NeurophysiologyManager.ShowReport";


    public static final String METHOD_CARDIO = "CardiopulmonaryManager.GetCardiopulmonaryExam";
    public static final String METHOD_CARDIO_SHOW_REPORT = "CardiopulmonaryManager.ShowReport";
    public static final String METHOD_CARDIO_SHOW_GRAPH = "CardiopulmonaryManager.ShowGraph";

    public static final String METHOD_CLINICAL_LAB = "LaboratoryManager.GetLabSpecimenList";
    public static final String METHOD_CLINICAL_LAB_DETAILS = "LaboratoryManager.GetLabSpecimenDetails";

    public static final String METHOD_SHOW_REPORT_DS = "PatientManager.ShowReport";
    public static final String METHOD_GET_PATIENT_VISIT = "PatientManager.GetPatientVisit";
    public static final String METHOD_DISCHARGE_SUMMARY_LIST = "PatientManager.GetDischargeSummaryList";

    public static final String METHOD_GET_ENDOSCOPY_LIST = "PatientManager.GetEndoscopyList";
    public static final String METHOD_GET_ENDOSCOPY_REPORT = "SharedManager.GetReport";

    public static final String METHOD_IMMUNIZATION_VACCINE_SCHEDULE = "PharmacyManager.VaccineSchedule";
    public static final String METHOD_IMMUNIZATION_UPDATE_VACCINE = "PharmacyManager.UpdateVaccine";
    public static final String METHOD_IMMUNIZATION_ADD_VACCINE = "PharmacyManager.AddVaccine";
    public static final String METHOD_IMMUNIZATION_VACCINE_IDS = "PharmacyManager.GetVaccineIDs";
    public static final String METHOD_IMMUNIZATION_ROUTE_IDS = "PharmacyManager.GetRouteIDs";

    public static final String METHOD_CURRENT_MEDICATION = "PharmacyManager.GetCurrentMedicine";
    public static final String METHOD_PREVIOUS_MEDICATION = "PharmacyManager.GetPreviousProfileMedications";
    public static final String METHOD_ADD_MEDICINE = "PharmacyManager.AddMedicine";
    public static final String METHOD_FREQUENCY_IDS = "PharmacyManager.GetFrequencyIDs";
    public static final String METHOD_PATIENT_ON_BOARD = "PatientManager.IsPatientOnBoard";

    public static final String METHOD_PATIENT_HEALTH_SUMMARY = "SharedManager.GetPatientHealthSummary";
    public static final String METHOD_DETAIL_HEALTH_SUMMARY = "SharedManager.GetHealthSummary";

    public static final String METHOD_PACS_MANAGER = "PACSManager.GetPacs";
    public static final String METHOD_PACS_ACCESSIONS = "{\"PACS_Accessions\":[";
    public static final String METHOD_PACS_ACCESSIONS_end = "]}";
//    44161191

    public static final String METHOD_VISIT_MENU = "SharedManager.GetVisitMenuList";

    public static final String Secret_token_paymentGatway = "96643d5010c848c6a0e7c6f2e2b342eb94c04052923d4d4697972e60a2b208439aed1efae87e4be8b4a1ed8232cc68f0e78dd08e030a4613bd86f01daeebd6996bb7eed245b1461a8a52bec74704e15aa11aba065ff4412bbd1ef9127b5d4e956c32a5b78b3d4a738cbcba64ae3ef13c7d09132df3474e209c4404e5a3c328c9";
}
