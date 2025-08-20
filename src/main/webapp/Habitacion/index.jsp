<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"/>

<c:set var="carpeta" value="${initParam.upload.path}"/>

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
                        <i class="ti ti-bell"></i>
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
                                 width="35" height="35" class="rounded-circle">
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
            <!--  Row 1 -->
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-md-flex align-items-center">
                                <div>
                                    <h4 class="card-title">Tabla Habitaciones</h4>
                                </div>
                                <div class="ms-auto mt-3 mt-md-0">
                                    <select class="form-select theme-select border-0"
                                            aria-label="Default select example">
                                        <option value="1">March 2025</option>
                                        <option value="2">March 2025</option>
                                        <option value="3">March 2025</option>
                                    </select>

                                    <div class="componente-registrar">
                                        <form action="${pageContext.request.contextPath}/habitacion" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="accion" value="cargar">
                                            <button type="submit" class="btn btn-primary m-1 btn-header-r">Nuevo</button>
                                        </form>
                                    </div>

                                </div>
                            </div>
                            <div class="table-responsive mt-4">
                                <table class="table mb-0 text-nowrap varient-table align-middle fs-3">
                                    <thead>
                                    <tr>
                                        <%--<th scope="col" class="px-0 text-muted">#</th>--%>
                                        <th scope="col" class="px-0 text-muted">N° habitación</th>
                                        <th scope="col" class="px-0 text-muted">Descripción</th>
                                        <th scope="col" class="px-0 text-muted">Tipo</th>
                                        <th scope="col" class="px-0 text-muted">Servicios</th>
                                        <th scope="col" class="px-0 text-muted">Estado</th>
                                        <th scope="col" class="px-0 text-muted text-end">Acciones</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:set var="contador" value="1"/>
                                    <c:forEach var="hs" items="${habitacionServicio}">
                                    <c:set var="h" value="${hs.habitacion}" />
                                        <tr>
                                            <%-- <td class="px-0">
                                                <div class="d-flex align-items-center">
                                                    <div class="ms-3">
                                                        <h6 class="mb-0">${contador}</h6>
                                                    </div>
                                                </div>
                                            </td>--%>

                                            <td class="px-0">
                                                <div class="d-flex align-items-center">
                                                    <img src="${pageContext.request.contextPath}/image?name=${h.imagen}" alt="Imagen" class="rounded-circle" width="120"
                                                         alt="flexy" />
                                                    <div class="ms-3">
                                                        <h6 class="mb-0 fw-bolder">${h.numeroHabitacion}</h6>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="px-0">${h.descripcion}</td>
                                            <td class="px-0">${h.tipoHabitacion.nombre}</td>
                                            <td class="px-0">
                                            <c:forEach var="s" items="${hs.servicios}">
                                            ${s.nombre} <br/>
                                            </c:forEach>
                                            </td>
                                            <td class="px-0">
                                                <c:choose>
                                                    <c:when test="${h.estado == 1}">
                                                        <span class="badge bg-info">Habilitada</span>
                                                    </c:when>
                                                    <c:when test="${h.estado == 0}">
                                                        <span class="badge bg-info">Deshabilitada</span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td class="px-0 text-dark fw-medium text-end">
                                                <form action="${pageContext.request.contextPath}/habitacion"
                                                      method="post" enctype="multipart/form-data">
                                                    <input type="hidden" name="accion" value="buscar">
                                                    <input type="hidden" name="id" value="${h.id}">
                                                    <button type="submit" class="btn btn-info m-1 w-85">Editar</button>
                                                </form>


                                                <form action="${pageContext.request.contextPath}/habitacion"
                                                      method="post" enctype="multipart/form-data">
                                                    <input type="hidden" name="id" value="${h.id}">
                                                    <input type="hidden" name="accion" value="cambiar">
                                                    <c:choose>
                                                        <c:when test="${h.estado == 1}">
                                                            <input type="hidden" name="estado" value="0">
                                                            <button type="submit" class="btn btn-danger m-1 w-85">
                                                                Deshabilitar
                                                            </button>
                                                        </c:when>

                                                        <c:when test="${h.estado == 0}">
                                                            <input type="hidden" name="estado" value="1">
                                                            <button type="submit" class="btn btn-success m-1 w-85">
                                                                Habilitar
                                                            </button>
                                                        </c:when>
                                                    </c:choose>
                                                </form>


                                            </td>
                                        </tr>
                                        <c:set var="contador" value="${contador + 1}"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>