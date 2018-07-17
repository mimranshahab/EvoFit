package edu.aku.ehs.models.receiving_model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitDetail implements Serializable
{


@SerializedName("IsPatientExists")
@Expose
private  Boolean isPatientExists ;

@Expose
private String recordFound;
@SerializedName("RecordMessage")
@Expose
private String recordMessage;
private final static long serialVersionUID = -996856004651374362L;

public Boolean getIsPatientExists() {
return isPatientExists;
}

public void setIsPatientExists(Boolean isPatientExists) {
this.isPatientExists = isPatientExists;
}


public String getRecordFound() {
return recordFound;
}

public void setRecordFound(String recordFound) {
this.recordFound = recordFound;
}

public String getRecordMessage() {
return recordMessage;
}

public void setRecordMessage(String recordMessage) {
this.recordMessage = recordMessage;
}

}