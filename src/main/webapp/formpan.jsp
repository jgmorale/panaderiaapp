<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Pan</title>
</head>
<body>
	<h2>Nuevo pan</h2>
	<form action="${action}" method="${method}">
		<input type="hidden" name ="txtId" value="${pan.getId()}">
		<div>
			<label for="txtNombre">Nombre</label>
			<input type="text" name="txtNombre" value="${pan.getNombre()}">
		</div>
		<div>
			<label for="txtDesc">Descripcion</label>
			<input type="text" name="txtDesc" value="${pan.getDescripcion()}">
		</div>
		<div>
			<label for="txtTamanho">Tamanho</label>
			<input type="text" name="txtTamanho" value="${pan.getTamanho()}">
		</div>
		<div>
			<label for="txtPrecio">Precio</label>
			<input type="text" name="txtPrecio" value="${pan.getPrecio()}">
		</div>
		<input type="submit" value="Agregar">
	</form>
</body>
</html>