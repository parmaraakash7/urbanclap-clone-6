package com.example.demo.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ModelType;
import com.example.demo.utils.ConnectionModel;

@RestController
@RequestMapping("/api/v1/sanitization/vashi")
public class SanitizingController {
	@GetMapping("")
    public ResponseEntity<List<ModelType>> getAll(){
    	List<ModelType> temp = new ArrayList<ModelType>();
    	 Connection c = null;
         Statement stmt = null;
         try {
            Class.forName("org.postgresql.Driver");
            
            c = ConnectionModel.getConnection();
            c.setAutoCommit(false);
            

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id,name,rating,rating_count,address,"
            		+ "verified,ST_AsGeoJSON(location) from sanitizing_vashi;" );
            
            while ( rs.next() ) {
               int id = rs.getInt("id");
               String  name = rs.getString("name");
               double rating = rs.getDouble("rating");
               int rating_count = rs.getInt("rating_count");
               String address = rs.getString("address");
               String verified = rs.getString("verified");
               String location = rs.getString("st_asgeojson");
               temp.add(new ModelType(id,name,rating,rating_count,address,verified,location));   
               
            }
            rs.close();
            stmt.close();
            c.close();
            return new ResponseEntity<>(temp, HttpStatus.OK);
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }
	
	@GetMapping("/nearest")
    public ResponseEntity<List<ModelType>> getAllNearest(@RequestParam(defaultValue="0.0") double longitude,
    		@RequestParam(defaultValue="0.0") double latitude,@RequestParam(defaultValue="2000") double dist){
    	List<ModelType> temp = new ArrayList<ModelType>();
    	 Connection c = null;
         Statement stmt = null;
         try {
            Class.forName("org.postgresql.Driver");
            
            c = ConnectionModel.getConnection();
            c.setAutoCommit(false);
            

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id,name,rating,rating_count,address,"
            		+ "verified,ST_AsGeoJSON(location) from sanitizing_vashi where ST_DWithin(location::geography,ST_GeomFromText('POINT("+longitude+" "+latitude+")')::geography,"+dist+");" );
            
            while ( rs.next() ) {
               int id = rs.getInt("id");
               String  name = rs.getString("name");
               double rating = rs.getDouble("rating");
               int rating_count = rs.getInt("rating_count");
               String address = rs.getString("address");
               String verified = rs.getString("verified");
               String location = rs.getString("st_asgeojson");
               temp.add(new ModelType(id,name,rating,rating_count,address,verified,location));   
               
            }
            rs.close();
            stmt.close();
            c.close();
            return new ResponseEntity<>(temp, HttpStatus.OK);
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }
	
	@GetMapping("/closest")
    public ResponseEntity<List<ModelType>> getAllClosestAscending(@RequestParam(defaultValue="0.0") double longitude,
    		@RequestParam(defaultValue="0.0") double latitude){
    	List<ModelType> temp = new ArrayList<ModelType>();
    	 Connection c = null;
         Statement stmt = null;
         ResultSet rs = null;
         try {
            Class.forName("org.postgresql.Driver");
            
            c = ConnectionModel.getConnection();
            c.setAutoCommit(false);
            

            stmt = c.createStatement();
            rs = stmt.executeQuery( "SELECT id,name,rating,rating_count,address,"
            		+ "verified,ST_AsGeoJSON(location),ST_Distance(location::geography,ST_GeomFromText('POINT("+longitude+" "+latitude+")')::geography) from sanitizing_vashi order by ST_Distance(location::geography,ST_GeomFromText('POINT("+longitude+" "+latitude+")')::geography); ");
            
            while ( rs.next() ) {
               int id = rs.getInt("id");
               String  name = rs.getString("name");
               double rating = rs.getDouble("rating");
               int rating_count = rs.getInt("rating_count");
               String address = rs.getString("address");
               String verified = rs.getString("verified");
               String location = rs.getString("st_asgeojson");
               double distance = rs.getDouble("st_distance");
               System.out.println(distance);
               temp.add(new ModelType(id,name,rating,rating_count,address,verified,location,distance));
               
               
            }
            rs.close();
            stmt.close();
            c.close();
            return new ResponseEntity<>(temp, HttpStatus.OK);
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }
}
