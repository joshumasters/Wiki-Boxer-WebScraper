import java.io.Serializable;

public class FightEntry implements Serializable {
    String fightNum;
    String result;
    String currentRecord;
    String opponent;
    String resultType;
    String roundTime;
    String date;
    String location;
    String notes;
    
    public FightEntry(String fightNum, String result, String record, String opponent, String resultType,
            String roundTime, String date, String location, String notes) {
        this.fightNum = fightNum;
        this.result = result;
        this.currentRecord = record;
        this.opponent = opponent;
        this.resultType = resultType;
        this.roundTime = roundTime;
        this.date = date;
        this.location = location;
        this.notes = notes;
    }

    public String getFightNum() {
        return fightNum;
    }

    public void setFightNum(String fightNum) {
        this.fightNum = fightNum;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecord() {
        return currentRecord;
    }

    public void setRecord(String record) {
        this.currentRecord = record;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "FightEntry [date=" + date + ", fightNum=" + fightNum + ", location=" + location + ", notes=" + notes
                + ", opponent=" + opponent + ", currentRecord=" + currentRecord + ", result=" + result + ", resultType=" + resultType
                + ", roundTime=" + roundTime + "]";
    }

    
    
}
