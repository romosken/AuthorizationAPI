//package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.rest.controller;
//
//
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.InvalidResourceException;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.dto.CommentDto;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.RatingDto;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.UserDto;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.commentmovie.CommentMovieRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.commentmovie.CommentMovieResponse;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.deletecomment.DeleteCommentRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.deletecomment.DeleteCommentResponse;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.likedislikemarkrepeatedcomment.LikeDislikeMarkRepeatedCommentRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.likedislikemarkrepeatedcomment.LikeDislikeMarkRepeatedCommentResponse;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.LogInRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.LogInResponse;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.ratemovie.VerifyTokenRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.ratemovie.VerifyTokenResponse;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.returnmoviecommentsrating.ReturnMovieCommentsRatingsRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.returnmoviecommentsrating.ReturnMovieCommentsRatingsResponse;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.upgradeuser.UpgradeUserRequest;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.upgradeuser.UpgradeUserResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Objects;
//
//@RestController
//@RequestMapping("/authorization/v1")
//@RequiredArgsConstructor
//public class AuthController {
//
//
//    @GetMapping("/login")
//    public ResponseEntity<LogInResponse> logInUser(
//            @RequestHeader String username,
//            @RequestHeader String password
//    ) {
//
//        return ResponseEntity.ok();
//
//    }
//
//    @GetMapping("/token/verify")
//    public ResponseEntity<VerifyTokenResponse> verifyToken(
//            @RequestHeader String token
//    ) {
//
//        return ResponseEntity.ok();
//
//    }
//
//}
//
//
