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
                    <h5 class="card-title fw-semibold mb-4">Editar Habitación</h5>
                    <div class="card">
                        <div class="card-body">
                            <c:set var="habitacion" value="${habitacionService.habitacion}" />
                            <form action="${pageContext.request.contextPath}/habitacion" method="post"
                                  enctype="multipart/form-data">
                                 <input type="hidden" value="${habitacion.id}" name="id">
                                <input type="hidden" value="modificar" name="accion">
                                <input type="hidden" value="${habitacion.imagen}" name="imageName">
                                <div class="mb-3">
                                    <label for="numero-habitacion" class="form-label"># habitacion</label>
                                    <input type="text" class="form-control" id="numero-habitacion"
                                           name="numero_habitacion"
                                           aria-describedby="emailHelp" value="${habitacion.numeroHabitacion}" required>
                                </div>

                                <div class="mb-3">
                                    <label for="capacidad" class="form-label">Capacidad</label>
                                    <input type="number" min="0" class="form-control" id="capacidad" name="capacidad"
                                           aria-describedby="emailHelp" value="${habitacion.capacidad}" required>
                                </div>

                                <div class=" mb-3">
                                    <label for="precio" class="form-label">Precio</label>
                                    <input type="number" min="0" class="form-control" id="precio" name="precio"
                                           aria-describedby="emailHelp" step="0.01" value="${habitacion.precio}" required>
                                </div>

                                <div class=" mb-3">
                                    <label for="piso" class="form-label">Piso</label>
                                    <input type="text" class="form-control" id="piso" name="piso"
                                           aria-describedby="emailHelp" value="${habitacion.piso}" required>
                                </div>

                                <div class=" mb-3">
                                    <label for="cama" class="form-label">Cantidad de camas</label>
                                    <input type="number" min="0" class="form-control" id="cama" name="cama"
                                           aria-describedby="emailHelp" value="${habitacion.cama}" required>
                                </div>

                                <div class=" mb-3">
                                    <label for="bw" class="form-label">Cantidad de baños</label>
                                    <input type="number" min="0" class="form-control" id="bw" name="bw"
                                           aria-describedby="emailHelp" value="${habitacion.bw}" required>
                                </div>

                                <div class=" mb-3">
                                    <labe for="tipo" class="form-label">Tipo de Habitación</labe>
                                    <select name="tipoHabitacion" class="form-select"
                                            aria-label="Default select example" required>
                                        <%--
                                        <option selected disabled> -- Seleccionar --</option>
                                        --%>
                                        <c:forEach var="t" items="${tipos}">
                                            <c:if test="${t.id == habitacion.tipoHabitacion.id && t.estado == 0}">
                                                <option value="${t.id}" ${t.id== habitacion.tipoHabitacion.id ?
                                                'selected="selected"' : ''}>${t.nombre}</option>
                                            </c:if>

                                            <c:if test="${t.estado == 1}">
                                                <option value="${t.id}" ${t.id== habitacion.tipoHabitacion.id ?
                                                'selected="selected"' : ''} >${t.nombre}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <labe for="servicio" class="form-label">Servicios</labe>
                                    <select name="servicio" class="form-select" aria-label="Default select example"
                                            multiple>
                                        <%--<option selected disabled> -- Seleccionar --</option>--%>

                                        <c:forEach var="s" items="${servicios}">
                                            <c:set var="selected" value="false"/>

                                            <c:forEach var="hs" items="${habitacionService.servicios}">
                                                <c:if test="${hs.id == s.id}">
                                                    <c:set var="selected" value="true"/>
                                                </c:if>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${s.estado == 1}">
                                                    <option value="${s.id}" ${selected == 'true' ? 'selected="selected"' : ''}>${s.nombre}</option>
                                                </c:when>

                                                <c:when test="${s.estado == 0 && selected == 'true'}">
                                                    <option value="${s.id}" ${selected == 'true' ? 'selected="selected"' : ''}>${s.nombre}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="descripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="descripcion" name="descripcion"
                                              rows="3" required>${habitacion.descripcion}</textarea>
                                </div>

                                <div class="mb-3">
                                    <div class="mb-3">
                                        <img src="${pageContext.request.contextPath}/image?name=${habitacion.imagen}" alt="Imagen" class="rounded" width="300" alt="flexy" />
                                    </div>

                                    <div>
                                        <label for="imagen" class="form-label">Imagen</label>
                                        <input class="form-control" type="file" id="imagen" name="imagen" accept="image/*">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Guardar cambios</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>