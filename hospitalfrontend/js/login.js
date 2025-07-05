document.getElementById("formLogin").addEventListener("submit", function (e) {
  e.preventDefault();

  const usuario = document.getElementById("usuario").value;
  const contrasena = document.getElementById("contrasena").value;

  fetch("http://localhost:8080/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: usuario,
      password: contrasena
    })
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Usuario o contraseña incorrectos");
      }
      return response.json();
    })
    .then(data => {
      // Guardamos el token JWT en localStorage
      localStorage.setItem("token", data.token);

      // Redirigir al usuario a la página protegida
      alert("Inicio de sesión exitoso");
      window.location.href = "gestion.html"; // Puedes cambiar esta ruta
    })
    .catch(error => {
      alert(error.message);
    });
});
