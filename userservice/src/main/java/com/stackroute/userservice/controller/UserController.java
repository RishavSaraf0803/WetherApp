package com.stackroute.userservice.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.jwtfilter.JwtProvider;
import com.stackroute.userservice.messages.JwtResponse;
import com.stackroute.userservice.messages.LoginForm;
import com.stackroute.userservice.messages.SignUpForm;
import com.stackroute.userservice.model.Role;
import com.stackroute.userservice.model.RoleName;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.repositroy.RoleRepository;
import com.stackroute.userservice.repositroy.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Username is already taken!",
                    HttpStatus.CONFLICT);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Email is already in use!",
                    HttpStatus.CONFLICT);
        }

        // Creating user's account
        UserModel user = new UserModel(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	switch(role) {
//	    		case "admin":
//	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
//	    			roles.add(adminRole);
//	    			
//	    			break;
//	    		case "pm":
//	            	Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
//	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//	            	roles.add(pmRole);
//	            	
//	    			break;
	    		default:
	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
	        		roles.add(userRole);        			
        	}
        });
        
        user.setRoles(roles);
        userRepository.save(user);

//        return ResponseEntity.ok().body("User registered successfully!");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("api/v1/user")
//public class UserController {
//
//	private UserServices userServices;
//
//	@Autowired
//	public UserController(UserServices userServices) {
//		this.userServices = userServices;
//	}
//	
//	@PostMapping("/register")
//	public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
//		
//		try {
//			userServices.registerUser(user);
//			return new ResponseEntity<>(HttpStatus.CREATED);
//		} catch (UserAlreadyExistsException e) {
//			return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody UserModel user) {
//		
//		Map<String, String> map = new HashMap<>();
//		try {
//			//user.setUserAddedDate(new Date());
//			if(userServices.findByUserNameAndPassword(user.getUserName(), user.getUserPassword()) != null) {
//				String jwtToken = getToken(user.getUserName(), user.getUserPassword());
//				map.put("token", jwtToken);
//				map.put("message", "user logged in successfully");
//				return new ResponseEntity<>(map,HttpStatus.OK);
//			}
//			
//		}catch(Exception e) {
//			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
//		}
//		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
//	}
//
//
//// Generate JWT token
//	public String getToken(String username, String password) throws Exception {
//			
//		Claims claims = Jwts.claims();
//		claims.put("username", username);
//		claims.put("password", password);
//		return Jwts.builder()
//				.setSubject("weatherapp")
//				.setClaims(claims)
//				.setExpiration(new Date(System.currentTimeMillis()+300000))
//				.signWith(SignatureAlgorithm.HS256, "weatherapp")
//				.compact();        
//        
//	}
//	
//	@GetMapping("/{userId}")
//	//@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
//	public ResponseEntity<?> getUser(@PathVariable("userId") int userId) {
//		
//		try {
//			if (userServices.getUserById(userId) != null) {
//				return new ResponseEntity<>(HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping("/")
//	//@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
//	public ResponseEntity<?> registerUser(@RequestBody UserModel user, HttpServletRequest request) {
//		
//		try {
//			this.userServices.registerUser(user);
//			return new ResponseEntity<>(HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}
//	}
//	
//	@PostMapping("/upload")
//	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
//
//		System.out.println("Original Image Byte Size - " + file.getBytes().length);
//		userServices.uploadImage(file);
//		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//				compressBytes(file.getBytes()));
//		imageRepository.save(img);
//		return ResponseEntity.status(HttpStatus.OK);
//	}
//
//	@GetMapping(path = { "/get/{imageName}" })
//	public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
//
//		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
//		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//				decompressBytes(retrievedImage.get().getPicByte()));
//		return img;
//	}

	// compress the image bytes before storing it in the database
//	public static byte[] compressBytes(byte[] data) {
//		Deflater deflater = new Deflater();
//		deflater.setInput(data);
//		deflater.finish();
//
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[1024];
//		while (!deflater.finished()) {
//			int count = deflater.deflate(buffer);
//			outputStream.write(buffer, 0, count);
//		}
//		try {
//			outputStream.close();
//		} catch (IOException e) {
//		}
//		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//
//		return outputStream.toByteArray();
//	}
//
//	// uncompress the image bytes before returning it to the angular application
//	public static byte[] decompressBytes(byte[] data) {
//		Inflater inflater = new Inflater();
//		inflater.setInput(data);
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[1024];
//		try {
//			while (!inflater.finished()) {
//				int count = inflater.inflate(buffer);
//				outputStream.write(buffer, 0, count);
//			}
//			outputStream.close();
//		} catch (IOException ioe) {
//		} catch (DataFormatException e) {
//		}
//		return outputStream.toByteArray();
//	}
	
	
	
	
	
//	@PutMapping("/api/v1/user/{id}")
////	@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
//	public ResponseEntity<?> updateUser(@PathVariable("id") String userId, @RequestBody UserModel user) {
//		
//		try {
//			if (userServices.updateUser(userId, user) != null) {
//				return new ResponseEntity<>(HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
//	@DeleteMapping("/api/v1/user/{id}")
//	//@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
//	public ResponseEntity<?> deleteUser(@PathVariable("id") String userId) {
//		
//		try {
//			if (userServices.deleteUser(userId)) {
//				return new ResponseEntity<>(HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		} catch (UserNotFoundException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//}

