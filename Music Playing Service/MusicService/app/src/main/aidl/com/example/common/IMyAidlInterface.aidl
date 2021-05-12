// IMyAidlInterface.aidl
package com.example.common;
import com.example.common.MySongs;
import android.os.Bundle;
import com.example.common.MySongs;
// Declare any non-default types here with import statements
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     List<MySongs> getAllSongs();
     MySongs getSpecificSong(int selected);
     String getUrl(int selected);
}