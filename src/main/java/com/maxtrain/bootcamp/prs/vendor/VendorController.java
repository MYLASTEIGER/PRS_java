package com.maxtrain.bootcamp.prs.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorController {
	@Autowired
	private VendorRepository vendRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Vendor>>GetVendors(){
		var v = vendRepo.findAll();
		return new ResponseEntity<Iterable<Vendor>>(v, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Vendor>GetVendor(@PathVariable int id){
		var v = vendRepo.findById(id);
		if(v.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	return new ResponseEntity<Vendor>(v.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Vendor> postVendor(@RequestBody Vendor vendor){
		if(vendor == null || vendor.getId() !=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var vendo = vendRepo.save(vendor);
		return new ResponseEntity<Vendor>(vendo, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putVendor(@PathVariable int id, @RequestBody Vendor vendor) {
		if(vendor == null || vendor.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var v = vendRepo.findById(vendor.getId());
		if(v.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendRepo.save(vendor);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteVendor(@PathVariable int id) {
		var v = vendRepo.findById(id);
		if(v.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendRepo.delete(v.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

	
