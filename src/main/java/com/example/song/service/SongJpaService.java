/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here
package com.example.song.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository; 
import com.example.song.model.Song;
@Service
public class SongJpaService implements SongRepository{
    @Autowired 
    private SongJpaRepository songJpaRepository;
    //getting all songs from  database 
    @Override 
    public ArrayList<Song> getAllSongs()
    {
            ArrayList<Song> list=new ArrayList<>(songJpaRepository.findAll());
            return list;
    }
    //getting specific song in database using songId
    @Override
    public Song getSongById(int songId)
    {
        try{
        return songJpaRepository.findById(songId).get();
        }
        catch(Exception e)
        {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    // add song to database
    @Override
    public Song addSong(Song song){
        try{
            return songJpaRepository.save(song);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // update the song from existing song in database
    @Override
    public Song updateSong(int songId, Song song){
        try{
            Song updatedSong=songJpaRepository.findById(songId).get();
            if(song.getSongName()!=null)
                updatedSong.setSongName(song.getSongName());
            if(song.getLyricist()!=null)
                updatedSong.setLyricist(song.getLyricist());
            if(song.getSinger()!=null)
                updatedSong.setSinger(song.getSinger());
            if(song.getMusicDirector()!=null)
                updatedSong.setMusicDirector(song.getMusicDirector());
            songJpaRepository.save(updatedSong);
            return updatedSong;

           }
            catch(Exception e)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

    // delete the song  from database
    @Override
    public void deleteSong(int songId){
        try {
            songJpaRepository.deleteById(songId);
        } 
        catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    
}
