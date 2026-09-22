package com.example.misapp.ui.screens

import android.annotation.SuppressLint
import android.util.Patterns

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VolumeUp

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.text.KeyboardOptions

import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip

import androidx.compose.ui.platform.LocalDensity



// ============================================================
// MISAPP LOGIN SCREEN
// ============================================================

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLogin: suspend (email: String, password: String) -> Result<Unit>
) {

    // ========================================================
    // COLORS
    // ========================================================

    val primaryBlue = Color(0xFF0B3D91)

    val brightBlue = Color(0xFF1268E8)

    val background = Color(0xFFF5F8FD)

    val darkText = Color(0xFF172033)

    val secondaryText = Color(0xFF5E6B82)

    val borderColor = Color(0xFFD8E0EF)

    val lightBlue = Color(0xFFEAF2FF)

    val successGreen = Color(0xFF00A86B)


    // ========================================================
    // STATES
    // ========================================================

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var emailError by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    var loginError by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }


    // ========================================================
    // COROUTINE
    // ========================================================

    val scope = rememberCoroutineScope()


    // ========================================================
    // LOGIN FUNCTION
    // ========================================================

    @SuppressLint("NewApi")
    fun validateAndLogin() {

        emailError = ""
        passwordError = ""
        loginError = ""


        if (email.isBlank()) {

            emailError =
                "Please enter your email address"

            return
        }


        if (
            !Patterns.EMAIL_ADDRESS
                .matcher(email.trim())
                .matches()
        ) {

            emailError =
                "Please enter a valid email address"

            return
        }


        if (password.isBlank()) {

            passwordError =
                "Please enter your password"

            return
        }


        if (password.length < 6) {

            passwordError =
                "Password must contain at least 6 characters"

            return
        }


        isLoading = true


        scope.launch {

            try {

                val result =
                    onLogin(
                        email.trim(),
                        password
                    )


                isLoading = false


                result
                    .onSuccess {

                        onLoginSuccess()
                    }
                    .onFailure { exception ->

                        loginError =
                            exception.message
                                ?: "Login failed. Please check your email and password."
                    }

            } catch (exception: Exception) {

                isLoading = false

                loginError =
                    exception.message
                        ?: "Login failed. Please try again."
            }
        }
    }


    // ========================================================
    // SCREEN
    // ========================================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {


        // ====================================================
        // BACKGROUND
        // ====================================================

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(
                        Color(0xFFE7F0FF)
                    )
            )
        }


        // ====================================================
        // MAIN CONTENT
        // ====================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {


            // =================================================
            // LOGO
            // =================================================

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Box(
                modifier = Modifier
                    .size(125.dp)
                    .clip(
                        RoundedCornerShape(60.dp)
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = "M",

                    color =
                        brightBlue,

                    fontSize =
                        78.sp,

                    fontWeight =
                        FontWeight.ExtraBold
                )
            }


            // =================================================
            // MISAPP
            // =================================================

            Text(
                text = "MISAPP",

                fontSize =
                    48.sp,

                fontWeight =
                    FontWeight.ExtraBold,

                letterSpacing =
                    4.sp,

                color =
                    primaryBlue
            )


            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )


            // =================================================
            // TAGLINE
            // =================================================

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                        .background(
                            primaryBlue
                        )
                )


                Spacer(
                    modifier =
                        Modifier.width(10.dp)
                )


                Text(
                    text =
                        "Smart IT, Simple Solutions",

                    fontSize =
                        15.sp,

                    color =
                        darkText,

                    fontWeight =
                        FontWeight.Medium
                )


                Spacer(
                    modifier =
                        Modifier.width(10.dp)
                )


                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                        .background(
                            primaryBlue
                        )
                )
            }


            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )


            // =================================================
            // ADVERTISEMENT BAR
            // =================================================

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),

                shape =
                    RoundedCornerShape(30.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            lightBlue
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 18.dp
                        ),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {


                    // =================================================
                    // SPEAKER
                    // =================================================

                    Icon(
                        imageVector =
                            Icons.Default.VolumeUp,

                        contentDescription =
                            "Advertisement",

                        tint =
                            primaryBlue,

                        modifier =
                            Modifier.size(27.dp)
                    )


                    Spacer(
                        modifier =
                            Modifier.width(14.dp)
                    )


                    // =================================================
                    // SCROLLING AREA
                    // =================================================

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp)
                            .clip(
                                RoundedCornerShape(14.dp)
                            ),

                        contentAlignment =
                            Alignment.CenterStart
                    ) {



// MOVING ADVERTISEMENT
// ===============================

                        val advertisement =
                            "Welcome to MISAPP • Smart IT, Simple Solutions • Technology you can trust • Professional IT & Networking Services"

                        var textWidth by remember { mutableStateOf(0) }

                        val density = LocalDensity.current

                        val infiniteTransition = rememberInfiniteTransition(label = "Advertisement")

                        val animatedX by infiniteTransition.animateFloat(
                            initialValue = 0f,
                            targetValue = -(textWidth + with(density) { 60.dp.toPx() }),
                            animationSpec = infiniteRepeatable(
                                animation = tween(
                                    durationMillis = 12000,
                                    easing = LinearEasing
                                ),
                                repeatMode = RepeatMode.Restart
                            ),
                            label = "AdvertisementMovement"
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = lightBlue
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 18.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "Advertisement",
                                    tint = primaryBlue,
                                    modifier = Modifier.size(27.dp)
                                )

                                Spacer(modifier = Modifier.width(14.dp))

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(20.dp))
                                ) {

                                    Row(
                                        modifier = Modifier.graphicsLayer {
                                            translationX = animatedX
                                        }
                                    ) {

                                        Text(
                                            text = advertisement,
                                            color = brightBlue,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Medium,
                                            maxLines = 1,
                                            softWrap = false,
                                            onTextLayout = {
                                                textWidth = it.size.width
                                            }
                                        )

                                        Spacer(
                                            modifier = Modifier.width(60.dp)
                                        )

                                        Text(
                                            text = advertisement,
                                            color = brightBlue,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Medium,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }


            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )


            // =================================================
            // LOGIN CARD
            // =================================================

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 16.dp,
                        shape =
                            RoundedCornerShape(28.dp)
                    ),

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 28.dp
                        ),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {


                    // =================================================
                    // LOCK ICON
                    // =================================================

                    Box(
                        modifier = Modifier
                            .size(82.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                            .background(
                                lightBlue
                            ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Lock,

                            contentDescription =
                                "Secure Login",

                            tint =
                                brightBlue,

                            modifier =
                                Modifier.size(43.dp)
                        )
                    }


                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )


                    // =================================================
                    // SIGN IN
                    // =================================================

                    Text(
                        text =
                            "Sign In",

                        fontSize =
                            36.sp,

                        fontWeight =
                            FontWeight.ExtraBold,

                        color =
                            darkText
                    )


                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )


                    Text(
                        text =
                            "Access your MISAPP account securely",

                        fontSize =
                            15.sp,

                        color =
                            secondaryText
                    )


                    Spacer(
                        modifier =
                            Modifier.height(28.dp)
                    )


                    // =================================================
                    // EMAIL
                    // =================================================

                    OutlinedTextField(
                        value =
                            email,

                        onValueChange = {

                            email =
                                it

                            emailError =
                                ""

                            loginError =
                                ""
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        singleLine =
                            true,

                        shape =
                            RoundedCornerShape(18.dp),

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Email
                            ),

                        leadingIcon = {

                            Icon(
                                imageVector =
                                    Icons.Default.Email,

                                contentDescription =
                                    "Email",

                                tint =
                                    brightBlue,

                                modifier =
                                    Modifier.size(27.dp)
                            )
                        },

                        label = {

                            Text(
                                text =
                                    "Email Address"
                            )
                        },

                        placeholder = {

                            Text(
                                text =
                                    "Enter your email",

                                color =
                                    secondaryText
                            )
                        },

                        isError =
                            emailError.isNotEmpty(),

                        colors =
                            OutlinedTextFieldDefaults.colors(

                                focusedBorderColor =
                                    brightBlue,

                                unfocusedBorderColor =
                                    borderColor,

                                focusedLabelColor =
                                    brightBlue,

                                unfocusedLabelColor =
                                    secondaryText,

                                cursorColor =
                                    brightBlue
                            )
                    )


                    if (
                        emailError.isNotEmpty()
                    ) {

                        Text(
                            text =
                                emailError,

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 8.dp,
                                        top = 5.dp
                                    ),

                            color =
                                Color.Red,

                            fontSize =
                                12.sp
                        )
                    }


                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )


                    // =================================================
                    // PASSWORD
                    // =================================================

                    OutlinedTextField(
                        value =
                            password,

                        onValueChange = {

                            password =
                                it

                            passwordError =
                                ""

                            loginError =
                                ""
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        singleLine =
                            true,

                        shape =
                            RoundedCornerShape(18.dp),

                        visualTransformation =
                            if (
                                passwordVisible
                            ) {

                                VisualTransformation.None

                            } else {

                                PasswordVisualTransformation()
                            },

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Password
                            ),

                        leadingIcon = {

                            Icon(
                                imageVector =
                                    Icons.Default.Lock,

                                contentDescription =
                                    "Password",

                                tint =
                                    brightBlue,

                                modifier =
                                    Modifier.size(27.dp)
                            )
                        },

                        trailingIcon = {

                            IconButton(
                                onClick = {

                                    passwordVisible =
                                        !passwordVisible
                                }
                            ) {

                                Icon(
                                    imageVector =
                                        if (
                                            passwordVisible
                                        ) {

                                            Icons.Default.VisibilityOff

                                        } else {

                                            Icons.Default.Visibility
                                        },

                                    contentDescription =
                                        if (
                                            passwordVisible
                                        ) {

                                            "Hide password"

                                        } else {

                                            "Show password"
                                        },

                                    tint =
                                        secondaryText
                                )
                            }
                        },

                        label = {

                            Text(
                                text =
                                    "Password"
                            )
                        },

                        placeholder = {

                            Text(
                                text =
                                    "Enter your password",

                                color =
                                    secondaryText
                            )
                        },

                        isError =
                            passwordError.isNotEmpty(),

                        colors =
                            OutlinedTextFieldDefaults.colors(

                                focusedBorderColor =
                                    brightBlue,

                                unfocusedBorderColor =
                                    borderColor,

                                focusedLabelColor =
                                    brightBlue,

                                unfocusedLabelColor =
                                    secondaryText,

                                cursorColor =
                                    brightBlue
                            )
                    )
                }


                if (
                    passwordError.isNotEmpty()
                ) {

                    Text(
                        text =
                            passwordError,

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 8.dp,
                                    top = 5.dp
                                ),

                        color =
                            Color.Red,

                        fontSize =
                            12.sp
                    )
                }


                // =================================================
                // FORGOT PASSWORD
                // =================================================

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.End
                ) {

                    TextButton(
                        onClick =
                            onForgotPasswordClick
                    ) {

                        Text(
                            text =
                                "Forgot Password?",

                            color =
                                brightBlue,

                            fontSize =
                                16.sp,

                            fontWeight =
                                FontWeight.SemiBold
                        )
                    }
                }


                // =================================================
                // LOGIN ERROR
                // =================================================

                if (
                    loginError.isNotEmpty()
                ) {

                    Text(
                        text =
                            loginError,

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    bottom = 10.dp
                                ),

                        color =
                            Color.Red,

                        fontSize =
                            13.sp
                    )
                }


                // =================================================
                // SIGN IN BUTTON
                // =================================================

                Button(
                    onClick = {

                        if (!isLoading) {

                            validateAndLogin()
                        }
                    },

                    enabled =
                        !isLoading,

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(62.dp),

                    shape =
                        RoundedCornerShape(18.dp),

                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                brightBlue,

                            contentColor =
                                Color.White
                        )
                ) {

                    if (isLoading) {

                        CircularProgressIndicator(
                            modifier =
                                Modifier.size(26.dp),

                            color =
                                Color.White,

                            strokeWidth =
                                3.dp
                        )

                    } else {

                        Icon(
                            imageVector =
                                Icons.Default.Login,

                            contentDescription =
                                "Sign In",

                            modifier =
                                Modifier.size(30.dp)
                        )


                        Spacer(
                            modifier =
                                Modifier.width(16.dp)
                        )


                        Text(
                            text =
                                "SIGN IN",

                            fontSize =
                                18.sp,

                            fontWeight =
                                FontWeight.Bold,

                            letterSpacing =
                                1.sp
                        )
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(22.dp)
                )


                // =================================================
                // SECURE ACCESS
                // =================================================

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.CheckCircle,

                        contentDescription =
                            "Secure access",

                        tint =
                            successGreen,

                        modifier =
                            Modifier.size(27.dp)
                    )


                    Spacer(
                        modifier =
                            Modifier.width(9.dp)
                    )


                    Text(
                        text =
                            "Secure access to MISAPP",

                        fontSize =
                            16.sp,

                        color =
                            secondaryText,

                        fontWeight =
                            FontWeight.Medium
                    )
                }
            }
        }


        Spacer(
            modifier =
                Modifier.height(18.dp)
        )


        // =================================================
        // REGISTER
        // =================================================

        Row(
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text =
                    "Don't have an account?",

                fontSize =
                    16.sp,

                color =
                    darkText
            )


            TextButton(
                onClick =
                    onRegisterClick
            ) {

                Text(
                    text =
                        "Register",

                    fontSize =
                        16.sp,

                    color =
                        brightBlue,

                    fontWeight =
                        FontWeight.Bold
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )
    }
}
