document.getElementById("formRegistro").addEventListener("submit", function (e) {
  e.preventDefault();

  const usuario = document.getElementById("usuario").value;
  const contrasena = document.getElementById("contrasena").value;
  const rol = document.getElementById("rol").value;

  fetch("http://localhost:8080/api/auth/registro", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: usuario,
      password: contrasena,
      rol: rol
    })
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Error al registrarse");
      }
      return response.json();
    })
    .then(data => {
      alert("Registro exitoso. Ahora puedes iniciar sesión.");
      window.location.href = "login.html";
    })
    .catch(error => {
      alert(error.message);
    });
});
