package com.starks.foodspots;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.starks.foodspots.models.FoodSpot;

/**
 * Created by monikapandey on 04/01/18.
 */

public class FoodSpotSuggestion implements SearchSuggestion {

    private String name;
    private boolean history = false;

    public FoodSpotSuggestion(String suggestion){
        this.name = suggestion.toLowerCase();
    }

    public FoodSpotSuggestion(Parcel source) {
        this.name = source.readString();
        this.history = source.readInt() != 0;
    }

    @Override
    public String getBody() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.history = isHistory;
    }

    public boolean getIsHistory() {
        return this.history;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(history ? 1 : 0);
    }

    public static final Creator<FoodSpotSuggestion> CREATOR = new Creator<FoodSpotSuggestion>() {
        @Override
        public FoodSpotSuggestion createFromParcel(Parcel in) {
            return new FoodSpotSuggestion(in);
        }

        @Override
        public FoodSpotSuggestion[] newArray(int size) {
            return new FoodSpotSuggestion[size];
        }
    };

}
