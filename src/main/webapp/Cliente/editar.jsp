<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"/>


<!--  Main wrapper -->
<div class="body-wrapper">
    <!--  Header Start -->
    <header class="app-header">
        <nav class="navbar navbar-expand-lg navbar-light">
            <ul class="navbar-nav">
                <li class="nav-item d-block d-xl-none">
                    <a class="nav-link sidebartoggler " id="headerCollapse" href="javascript:void(0)">
                        <i class="ti ti-menu-2"></i>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link " href="javascript:void(0)" id="drop1" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <iconify-icon icon="solar:bell-linear" class="fs-6"></iconify-icon>
                        <div class="notification bg-primary rounded-circle"></div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-animate-up" aria-labelledby="drop1">
                        <div class="message-body">
                            <a href="javascript:void(0)" class="dropdown-item">
                                Item 1
                            </a>
                            <a href="javascript:void(0)" class="dropdown-item">
                                Item 2
                            </a>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">

                    <li class="nav-item dropdown">
                        <a class="nav-link " href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <img src="${pageContext.request.contextPath}/assets/images/profile/user-1.jpg" alt=""
                                 width="35" height="35"
                                 class="rounded-circle">
                        </a>
                        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                            <div class="message-body">
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-user fs-6"></i>
                                    <p class="mb-0 fs-3">My Profile</p>
                                </a>
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-mail fs-6"></i>
                                    <p class="mb-0 fs-3">My Account</p>
                                </a>
                                <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                    <i class="ti ti-list-check fs-6"></i>
                                    <p class="mb-0 fs-3">My Task</p>
                                </a>
                                <a href="./authentication-login.html" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!--  Header End -->
    <div class="body-wrapper-inner">
        <div class="container-fluid">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title fw-semibold mb-4">Editar Cliente</h5>
                    <div class="card">
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/cliente" method="post">
                                <input type="hidden" value="modificar" name="accion">
                                <input type="hidden" value="${cliente.id}" name="id">
                                <input type="hidden" value="${cliente.usuario.id}" name="id_usuario">
                                <div class="mb-3">
                                    <label for="nombre" class="form-label">Nombre</label>
                                    <input type="text" class="form-control" id="nombre" name="nombre"
                                           aria-describedby="emailHelp" value="${cliente.nombre}" required>
                                </div>

                                <div class="mb-3">
                                    <label for="apellido" class="form-label">Apellido</label>
                                    <input type="text" class="form-control" id="apellido" name="apellido"
                                           aria-describedby="emailHelp" value="${cliente.apellido}" required>
                                </div>

                                <div class="mb-3">
                                     <label for="email" class="form-label">Correo electrónico</label>
                                     <input type="email" class="form-control" id="email" name="email"
                                            aria-describedby="emailHelp" value="${cliente.usuario.email}" required>
                                </div>

                                <div class="mb-3">
                                    <label for="telefono" class="form-label">Teléfono</label>
                                    <input type="tel" class="form-control" id="telefono" name="telefono"
                                           aria-describedby="emailHelp" value="${cliente.telefono}" required>
                                </div>


                                <div class="mb-3">
                                    <label for="dui" class="form-label">Documento Unico de Identidad</label>
                                    <input type="text" class="form-control" id="dui" name="dui"
                                           aria-describedby="emailHelp"
                                           placeholder="########-#"
                                           value="${empty cliente.dui ? '' : cliente.dui}">
                                </div>

                                <div class="mb-3">
                                    <label for="pasaporte" class="form-label">Pasaporte (Extranjero)</label>
                                    <input type="text" class="form-control" id="pasaporte" name="pasaporte"
                                           aria-describedby="emailHelp"
                                           value="${empty cliente.pasaporte ? '' : cliente.pasaporte}"
                                           placeholder="##############">
                                </div>

                                <div class="mb-3">
                                    <label for="fecha_nacimiento" class="form-label">Fecha de nacimiento</label>
                                    <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento"
                                           aria-describedby="emailHelp"
                                           value="${cliente.fechaNacimiento}"
                                           required>
                                </div>

                                <div class="mb-3">
                                    <label for="nacionalidad" class="form-label">Nacionalidad</label>
                                    <input type="text" class="form-control" id="nacionalidad" name="nacionalidad"
                                           aria-describedby="emailHelp"
                                           value="${cliente.nacionalidad}"
                                           required>
                                </div>

                                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>