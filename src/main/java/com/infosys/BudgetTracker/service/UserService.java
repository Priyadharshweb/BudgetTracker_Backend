package com.infosys.BudgetTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.BudgetTracker.entity.User;
import com.infosys.BudgetTracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	public List<User> fetchUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public User findById(long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElse(null);
	}

	public User creatingUser(User data) {
		// TODO Auto-generated method stub
		return userRepo.save(data);
	}

	public String deletingUser(long id) {
		// TODO Auto-generated method stub
		if(userRepo.existsById(id))
        {
              userRepo.deleteById(id);
              return "id deleted succefully";
        }
        else
        {
            return id+ "Data not found";
        }
        
	}

	public String updatingUser(long id, User data2) {
	    Optional<User> optionalUser = userRepo.findById(id);
	    
	    if (optionalUser.isPresent()) {
	        User existingUser = optionalUser.get();

	        // ✅ Update only necessary fields
	        existingUser.setName(data2.getName());
	        existingUser.setEmail(data2.getEmail());
	        existingUser.setPassword(data2.getPassword());
	        // Add more fields as needed

	        userRepo.save(existingUser); // ✅ Save updated user

	        return "User updated successfully";
	    } else {
	        return "User not found";
	    }
	}


}
