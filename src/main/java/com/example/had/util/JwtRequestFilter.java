package com.example.had.util;

//import com.example.had.jwtservices.DoctorDetailsService;
import com.example.had.jwtservices.SupervisorDetailsService;
import com.example.had.services.DoctorInHospitalService;
import com.example.had.services.FieldWorkerInHospitalService;
import com.example.had.services.SupervisorService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SupervisorDetailsService supervisorDetailsService;

    @Autowired
    private FieldWorkerInHospitalService fieldWorkerInHospitalService;

//    @Autowired
//    private DoctorDetailsService doctorDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        String actor = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            actor = authorizationHeader.substring(7,12);
            jwt = authorizationHeader.substring(13);
            System.out.println(jwt);
            username = jwtUtil.extractUsername(jwt);
        }
        System.out.println(actor);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            if (actor.equals("Super")) {
                System.out.println("supervisor");
                userDetails = this.supervisorDetailsService.loadUserByUsername(username);
            }
//            if(actor.equals("Doctr")){
//                System.out.println("doctor");
////                System.out.println(username);
//                userDetails = this.doctorDetailsService.loadUserByUsername(username);
//                System.out.println(userDetails);
//            }
//            else if(actor.equals("Field")){
//                System.out.println(username);
//                System.out.println("field worker");
//                userDetails = this.fieldWorkerInHospitalService.loadUserByUsername(username);
//
//            }
//            else if(actor.equals("Admin")){
//                // implementation incoming
//            }

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
