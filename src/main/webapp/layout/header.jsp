<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hotel - heisenberg</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/logos/favicon.png"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.min.css"/>
</head>

<body>
<!--  Body Wrapper -->
<div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed" data-header-position="fixed">

    <!--  App Topstrip -->
    <div class="app-topstrip bg-dark py-6 px-3 w-100 d-lg-flex align-items-center justify-content-between">
        <div class="d-flex align-items-center justify-content-center gap-5 mb-2 mb-lg-0">
            <a class="d-flex justify-content-center" href="${pageContext.request.contextPath}/">
                <!-- <img src="${pageContext.request.contextPath}/assets/images/logos/logo-wrappixel.svg" alt="" width="150"> -->
                <p class="text-white fw-bolder fs-6 mt-2 mb-0">Hotel Heisenberg</p>
            </a>
        </div>

    </div>
    <!-- Sidebar Start -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div>
            <div class="brand-logo d-flex align-items-center justify-content-between">
                <a href="${pageContext.request.contextPath}/" class="text-nowrap logo-img">
                    <img src="${pageContext.request.contextPath}/assets/images/logos/logo.svg" alt=""/>
                </a>
                <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                    <i class="ti ti-x fs-6"></i>
                </div>
            </div>
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
                <ul id="sidebarnav">
                    <li class="nav-small-cap">
                        <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                        <span class="hide-menu">Gestión de personas</span>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/cliente" aria-expanded="false">
                            <i class="ti ti-atom"></i>
                            <span class="hide-menu">Clientes</span>
                        </a>
                    </li>
                    <!-- ---------------------------------- -->
                    <!-- Dashboard -->
                    <!-- ---------------------------------- -->
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between" href="#" aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-user-circle"></i>
                  </span>
                                <span class="hide-menu">Empleados</span>
                            </div>

                        </a>
                    </li>


                    <li>
                        <span class="sidebar-divider lg"></span>
                    </li>
                    <li class="nav-small-cap">
                        <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                        <span class="hide-menu">Gestión de reservación</span>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between" href="#" aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-shopping-cart"></i>
                  </span>
                                <span class="hide-menu">Reservaciones</span>
                            </div>

                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between" href="#" aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-currency-dollar"></i>
                  </span>
                                <span class="hide-menu">Pagos</span>
                            </div>

                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between" href="#" aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-file-text"></i>
                  </span>
                                <span class="hide-menu">Facturas</span>
                            </div>

                        </a>
                    </li>

                    <li>
                        <span class="sidebar-divider lg"></span>
                    </li>
                    <li class="nav-small-cap">
                        <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                        <span class="hide-menu">Gestión de Habitaciones</span>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between" href="${pageContext.request.contextPath}/tipoHabitacion" aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-qrcode"></i>
                  </span>
                     <span class="hide-menu">Tipo de Habitaciones</span>
                  </div>
                  </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between" href="${pageContext.request.contextPath}/habitacion" aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-app-window"></i>
                  </span>
                                <span class="hide-menu">Habitaciones</span>
                            </div>

                        </a>
                    </li>

                    <li>
                        <span class="sidebar-divider lg"></span>
                    </li>
                    <li class="nav-small-cap">
                        <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                        <span class="hide-menu">Gestión de servicios</span>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/tipoServicio" aria-expanded="false">
                            <i class="ti ti-layers-subtract"></i>
                            <span class="hide-menu">Tipo de Servicios</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/servicio" aria-expanded="false">
                            <i class="ti ti-phone"></i>
                            <span class="hide-menu">Servicios</span>
                        </a>
                    </li>

                    <li>
                        <span class="sidebar-divider lg"></span>
                    </li>
                    <li class="nav-small-cap">
                        <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                        <span class="hide-menu">Gestión de Estadísticas</span>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between has-arrow" href="javascript:void(0)"
                           aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-chart-line"></i>
                  </span>
                                <span class="hide-menu">Clientes</span>
                            </div>

                        </a>
                        <ul aria-expanded="false" class="collapse first-level">
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Forms Input</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Input Groups</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Input Grid</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Checkbox & Radios</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Bootstrap Switch</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Select2</span>
                                    </div>

                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between has-arrow" href="javascript:void(0)"
                           aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-chart-area"></i>
                  </span>
                                <span class="hide-menu">Empleados</span>
                            </div>

                        </a>
                        <ul aria-expanded="false" class="collapse first-level">

                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Dropzone</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Mask</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Typehead</span>
                                    </div>

                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between has-arrow" href="javascript:void(0)"
                           aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-chart-bar"></i>
                  </span>
                                <span class="hide-menu">Reservaciones</span>
                            </div>

                        </a>
                        <ul aria-expanded="false" class="collapse first-level">
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Basic Form</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Horizontal</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Actions</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Row Separator</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Bordered</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Detail</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Striped Rows</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Form Floating Input</span>
                                    </div>

                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between has-arrow" href="javascript:void(0)"
                           aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-chart-arcs"></i>
                  </span>
                                <span class="hide-menu">Servicios</span>
                            </div>

                        </a>
                        <ul aria-expanded="false" class="collapse first-level">
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Bootstrap Validation</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Custom Validation</span>
                                    </div>

                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link justify-content-between has-arrow" href="javascript:void(0)"
                           aria-expanded="false">
                            <div class="d-flex align-items-center gap-3">
                  <span class="d-flex">
                    <i class="ti ti-chart-radar"></i>
                  </span>
                                <span class="hide-menu">Habitaciones</span>
                            </div>

                        </a>
                        <ul aria-expanded="false" class="collapse first-level">
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Colorpicker</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">Rangepicker</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">BT Datepicker</span>
                                    </div>

                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#">
                                    <div class="d-flex align-items-center gap-3">
                                        <div class="round-16 d-flex align-items-center justify-content-center">
                                            <i class="ti ti-circle"></i>
                                        </div>
                                        <span class="hide-menu">MT Datepicker</span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!--  Sidebar End -->