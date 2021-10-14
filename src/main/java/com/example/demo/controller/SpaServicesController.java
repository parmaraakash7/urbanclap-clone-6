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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ModelType;
import com.example.demo.utils.ConnectionModel;

@RestController
@RequestMapping("/api/v1/spa")
public class SpaServicesController {
	@GetMapping("/vashi")
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
            		+ "verified,ST_AsGeoJSON(location) from spa_services_vashi;" );
            
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
}
