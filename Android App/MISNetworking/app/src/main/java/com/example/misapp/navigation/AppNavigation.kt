package com.example.misapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.misapp.data.model.ServiceRequest

import com.example.misapp.ui.screens.AdminDashboardScreen
import com.example.misapp.ui.screens.AdminServiceRequestsScreen
import com.example.misapp.ui.screens.ClientDashboardScreen
import com.example.misapp.ui.screens.ClientsScreen
import com.example.misapp.ui.screens.ForgotPasswordScreen
import com.example.misapp.ui.screens.LoginScreen
import com.example.misapp.ui.screens.MyServiceRequestsScreen
import com.example.misapp.ui.screens.RegisterScreen
import com.example.misapp.ui.screens.ServiceRequestClientDetailsScreen
import com.example.misapp.ui.screens.ServiceRequestDetailsScreen
import com.example.misapp.ui.screens.ServiceRequestScreen
import com.example.misapp.ui.screens.SplashScreen
import com.example.misapp.ui.screens.SystemSettingsScreen
import com.example.misapp.ui.screens.TechnicianAssignedRequestsScreen
import com.example.misapp.ui.screens.TechnicianDashboardScreen
import com.example.misapp.ui.screens.TechnicianRequestDetailsScreen
import com.example.misapp.ui.screens.TechniciansScreen

import com.example.misapp.viewmodel.LoginViewModel
import com.example.misapp.viewmodel.UserSessionViewModel

import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine


object AppRoutes {

    // ========================================
    // GENERAL
    // ========================================

    const val SPLASH = "splash"

    const val LOGIN = "login"

    const val REGISTER = "register"

    const val FORGOT_PASSWORD = "forgot_password"


    // ========================================
    // CLIENT
    // ========================================

    const val CLIENT_DASHBOARD =
        "client_dashboard"

    const val SERVICE_REQUEST =
        "service_request"

    const val MY_REQUESTS =
        "my_requests"

    const val CLIENT_SERVICE_REQUEST_DETAILS =
        "client_service_request_details"


    // ========================================
    // ADMIN
    // ========================================

    const val ADMIN_DASHBOARD =
        "admin_dashboard"

    const val ADMIN_SERVICE_REQUESTS =
        "admin_service_requests"

    const val SERVICE_REQUEST_DETAILS =
        "service_request_details"


    // ========================================
    // TECHNICIAN
    // ========================================

    const val TECHNICIAN_DASHBOARD =
        "technician_dashboard"

    const val TECHNICIAN_REQUESTS =
        "technician_requests"

    const val TECHNICIAN_REQUEST_DETAILS =
        "technician_request_details"

    const val TECHNICIANS =
        "technicians"

    const val CLIENTS =
        "clients"

    const val SYSTEM_SETTINGS =
        "system_settings"
}


@Composable
fun AppNavigation() {

    val navController =
        rememberNavController()


    // ========================================
    // USER SESSION VIEWMODEL
    // ========================================

    val userSessionViewModel:
            UserSessionViewModel = viewModel()


    // ========================================
    // LOGIN VIEWMODEL
    // ========================================

    val loginViewModel:
            LoginViewModel = viewModel()


    // ========================================
    // SELECTED REQUEST
    // ========================================

    var selectedRequest by remember {

        mutableStateOf<ServiceRequest?>(null)
    }


    // ========================================
    // NAVIGATION HOST
    // ========================================

    NavHost(

        navController = navController,

        startDestination =
            AppRoutes.SPLASH
    ) {


        // ==================================================
        // SPLASH
        // ==================================================

        composable(
            AppRoutes.SPLASH
        ) {

            SplashScreen(

                onSplashFinished = {

                    navController.navigate(
                        AppRoutes.LOGIN
                    ) {

                        popUpTo(
                            AppRoutes.SPLASH
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // ==================================================
// LOGIN
// ==================================================

        composable(
            AppRoutes.LOGIN
        ) {

            LoginScreen(

                // ------------------------------------------
                // LOGIN
                // ------------------------------------------

                onLogin = { email, password ->

                    suspendCancellableCoroutine { continuation ->

                        loginViewModel.login(

                            email = email,

                            password = password,

                            onSuccess = {

                                continuation.resume(
                                    Result.success(Unit)
                                )
                            },

                            onError = { errorMessage ->

                                continuation.resume(
                                    Result.failure(
                                        Exception(errorMessage)
                                    )
                                )
                            }
                        )
                    }
                },


                // ------------------------------------------
                // REGISTER
                // ------------------------------------------

                onRegisterClick = {

                    navController.navigate(
                        AppRoutes.REGISTER
                    )
                },


                // ------------------------------------------
                // FORGOT PASSWORD
                // ------------------------------------------

                onForgotPasswordClick = {

                    navController.navigate(
                        AppRoutes.FORGOT_PASSWORD
                    )
                },


                // ------------------------------------------
                // LOGIN SUCCESS
                // ------------------------------------------

                onLoginSuccess = {

                    userSessionViewModel.loadCurrentUser(

                        onSuccess = { profile ->

                            when (
                                profile.role.lowercase()
                            ) {

                                // ==================================
                                // CLIENT
                                // ==================================

                                "client" -> {

                                    navController.navigate(
                                        AppRoutes.CLIENT_DASHBOARD
                                    ) {

                                        popUpTo(
                                            AppRoutes.LOGIN
                                        ) {
                                            inclusive = true
                                        }
                                    }
                                }


                                // ==================================
                                // ADMIN
                                // ==================================

                                "admin" -> {

                                    navController.navigate(
                                        AppRoutes.ADMIN_DASHBOARD
                                    ) {

                                        popUpTo(
                                            AppRoutes.LOGIN
                                        ) {
                                            inclusive = true
                                        }
                                    }
                                }


                                // ==================================
                                // TECHNICIAN
                                // ==================================

                                "technician" -> {

                                    navController.navigate(
                                        AppRoutes.TECHNICIAN_DASHBOARD
                                    ) {

                                        popUpTo(
                                            AppRoutes.LOGIN
                                        ) {
                                            inclusive = true
                                        }
                                    }
                                }


                                // ==================================
                                // UNKNOWN ROLE
                                // ==================================

                                else -> {

                                    // Unsupported role
                                }
                            }
                        },

                        onError = {

                            // Error handling
                        }
                    )
                }
            )
        }
        // ==================================================
        // REGISTER
        // ==================================================

        composable(
            AppRoutes.REGISTER
        ) {

            RegisterScreen(

                onLoginClick = {

                    navController.navigate(
                        AppRoutes.LOGIN
                    ) {

                        popUpTo(
                            AppRoutes.REGISTER
                        ) {

                            inclusive = true
                        }
                    }
                },


                onRegistrationSuccess = {

                    navController.navigate(
                        AppRoutes.LOGIN
                    ) {

                        popUpTo(
                            AppRoutes.REGISTER
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // ==================================================
        // FORGOT PASSWORD
        // ==================================================

        composable(
            AppRoutes.FORGOT_PASSWORD
        ) {

            ForgotPasswordScreen(

                onBackToLogin = {

                    navController.navigate(
                        AppRoutes.LOGIN
                    ) {

                        popUpTo(
                            AppRoutes.FORGOT_PASSWORD
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // ==================================================
        // CLIENT DASHBOARD
        // ==================================================

        composable(
            AppRoutes.CLIENT_DASHBOARD
        ) {

            val profile =
                userSessionViewModel
                    .currentUserProfile


            if (profile != null) {

                ClientDashboardScreen(

                    fullName =
                        profile.fullName,


                    // --------------------------------------
                    // LOGOUT
                    // --------------------------------------

                    onLogout = {

                        userSessionViewModel.logout()

                        navController.navigate(
                            AppRoutes.LOGIN
                        ) {

                            popUpTo(
                                navController.graph.id
                            ) {

                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    },


                    // --------------------------------------
                    // REQUEST SERVICE
                    // --------------------------------------

                    onRequestService = {

                        navController.navigate(
                            AppRoutes.SERVICE_REQUEST
                        )
                    },


                    // --------------------------------------
                    // MY REQUESTS
                    // --------------------------------------

                    onMyRequests = {

                        navController.navigate(
                            AppRoutes.MY_REQUESTS
                        )
                    }
                )
            }
        }


        // ==================================================
        // CLIENT - CREATE SERVICE REQUEST
        // ==================================================

        composable(
            AppRoutes.SERVICE_REQUEST
        ) {

            val profile =
                userSessionViewModel
                    .currentUserProfile


            if (profile != null) {

                ServiceRequestScreen(

                    clientId =
                        profile.uid,

                    clientName =
                        profile.fullName,

                    clientEmail =
                        profile.email,


                    onBack = {

                        navController.popBackStack()
                    },


                    onRequestSubmitted = {

                        navController.popBackStack()
                    }
                )
            }
        }


        // ==================================================
        // CLIENT - MY SERVICE REQUESTS
        // ==================================================

        composable(
            AppRoutes.MY_REQUESTS
        ) {

            val profile =
                userSessionViewModel
                    .currentUserProfile


            if (profile != null) {

                MyServiceRequestsScreen(

                    clientId =
                        profile.uid,


                    onBack = {

                        navController.popBackStack()
                    },


                    onRequestClick = { request ->

                        selectedRequest =
                            request


                        navController.navigate(
                            AppRoutes.CLIENT_SERVICE_REQUEST_DETAILS
                        )
                    }
                )
            }
        }


        // ==================================================
        // CLIENT - REQUEST DETAILS
        // ==================================================

        composable(
            AppRoutes.CLIENT_SERVICE_REQUEST_DETAILS
        ) {

            val request =
                selectedRequest


            if (request != null) {

                ServiceRequestClientDetailsScreen(

                    request =
                        request,


                    onBack = {

                        navController.popBackStack()
                    }
                )
            }
        }


        // ==================================================
        // ADMIN DASHBOARD
        // ==================================================

        composable(
            AppRoutes.ADMIN_DASHBOARD
        ) {

            val profile =
                userSessionViewModel
                    .currentUserProfile


            if (profile != null) {

                AdminDashboardScreen(

                    fullName =
                        profile.fullName,


                    // --------------------------------------
                    // ADMIN LOGOUT
                    // --------------------------------------

                    onLogout = {

                        userSessionViewModel.logout()

                        navController.navigate(
                            AppRoutes.LOGIN
                        ) {

                            popUpTo(
                                navController.graph.id
                            ) {

                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    },


                    // --------------------------------------
                    // SERVICE REQUESTS
                    // --------------------------------------

                    onServiceRequests = {

                        navController.navigate(
                            AppRoutes.ADMIN_SERVICE_REQUESTS
                        )
                    },


                    // --------------------------------------
                    // TECHNICIANS
                    // --------------------------------------

                    onTechnicians = {

                        navController.navigate(
                            AppRoutes.TECHNICIANS
                        )
                    },


                    // --------------------------------------
                    // CLIENTS
                    // --------------------------------------

                    onClients = {

                        navController.navigate(
                            AppRoutes.CLIENTS
                        )
                    },


                    // --------------------------------------
                    // SYSTEM SETTINGS
                    // --------------------------------------

                    onSystemSettings = {

                        navController.navigate(
                            AppRoutes.SYSTEM_SETTINGS
                        )
                    }
                )
            }
        }


        // ==================================================
        // ADMIN - SERVICE REQUESTS
        // ==================================================

        composable(
            AppRoutes.ADMIN_SERVICE_REQUESTS
        ) {

            AdminServiceRequestsScreen(

                onBack = {

                    navController.popBackStack()
                },


                onRequestClick = { request ->

                    selectedRequest =
                        request


                    navController.navigate(
                        AppRoutes.SERVICE_REQUEST_DETAILS
                    )
                }
            )
        }


        // ==================================================
        // ADMIN - REQUEST DETAILS
        // ==================================================

        composable(
            AppRoutes.SERVICE_REQUEST_DETAILS
        ) {

            val request =
                selectedRequest


            if (request != null) {

                ServiceRequestDetailsScreen(

                    request =
                        request,


                    onBack = {

                        navController.popBackStack()
                    }
                )
            }
        }


        // ==================================================
        // TECHNICIAN DASHBOARD
        // ==================================================

        composable(
            AppRoutes.TECHNICIAN_DASHBOARD
        ) {

            val profile =
                userSessionViewModel
                    .currentUserProfile


            if (profile != null) {

                TechnicianDashboardScreen(

                    fullName =
                        profile.fullName,


                    // --------------------------------------
                    // MY ASSIGNED REQUESTS
                    // --------------------------------------

                    onMyRequests = {

                        navController.navigate(
                            AppRoutes.TECHNICIAN_REQUESTS
                        )
                    },


                    // --------------------------------------
                    // LOGOUT
                    // --------------------------------------

                    onLogout = {

                        userSessionViewModel.logout()

                        navController.navigate(
                            AppRoutes.LOGIN
                        ) {

                            popUpTo(
                                navController.graph.id
                            ) {

                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }
        }


        // ==================================================
        // TECHNICIAN - ASSIGNED REQUESTS
        // ==================================================

        composable(
            AppRoutes.TECHNICIAN_REQUESTS
        ) {

            val profile =
                userSessionViewModel
                    .currentUserProfile


            if (profile != null) {

                TechnicianAssignedRequestsScreen(

                    technicianId =
                        profile.uid,


                    onBack = {

                        navController.popBackStack()
                    },


                    onRequestClick = { request ->

                        selectedRequest =
                            request


                        navController.navigate(
                            AppRoutes.TECHNICIAN_REQUEST_DETAILS
                        )
                    }
                )
            }
        }


        // ==================================================
        // TECHNICIAN - REQUEST DETAILS
        // ==================================================

        composable(
            AppRoutes.TECHNICIAN_REQUEST_DETAILS
        ) {

            val request =
                selectedRequest


            if (request != null) {

                TechnicianRequestDetailsScreen(

                    request =
                        request,


                    onBack = {

                        navController.popBackStack()
                    }
                )
            }
        }


        // ==================================================
        // TECHNICIANS
        // ==================================================

        composable(
            AppRoutes.TECHNICIANS
        ) {

            TechniciansScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }


        // ==================================================
        // CLIENTS
        // ==================================================

        composable(
            AppRoutes.CLIENTS
        ) {

            ClientsScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }


        // ==================================================
        // SYSTEM SETTINGS
        // ==================================================

        composable(
            AppRoutes.SYSTEM_SETTINGS
        ) {

            SystemSettingsScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }
    }
}