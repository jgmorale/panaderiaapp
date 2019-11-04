<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar pan</title>
</head>
<body>
	<form action="modPan" method="post">
		<div>
			<label for="txtId">Id</label>
			<input type="text" name="txtId">
		</div>
		<div>
			<label for="txtNombre">Nombre</label>
			<input type="text" name="txtNombre">
		</div>
		<div>
			<label for="txtDesc">Descripcion</label>
			<input type="text" name="txtDesc">
		</div>
		<div>
			<label for="txtTamanho">Tamaño</label>
			<input type="text" name="txtTamanho">
		</div>
		<div>
			<label for="txtPrecio">Precio</label>
			<input type="text" name="txtPrecio">
		</div>
		<input type="submit" value="Modificar">
	</form>

</body>
</html>