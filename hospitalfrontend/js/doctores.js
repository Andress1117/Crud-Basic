document.addEventListener("DOMContentLoaded", () => {
  const url = "http://localhost:8080/doctores";
  const tabla = document.getElementById("tabla");
  const formulario = document.getElementById("formulario");
  const buscar = document.getElementById("buscar");

  const formEditar = document.getElementById("formEditar");

  const obtenerDoctores = async () => {
    try {
      const res = await fetch(url);
      const doctores = await res.json();

      tabla.innerHTML = "";
      doctores.forEach(doc => {
        tabla.innerHTML += `
          <tr>
            <td>${doc.nombre}</td>
            <td>${doc.especialidad}</td>
            <td>${doc.telefono}</td>
            <td>${doc.correo}</td>
            <td>
              <button class="btn btn-warning btn-sm" onclick='editarDoctor(${JSON.stringify(doc)})'>Editar</button>
              <button class="btn btn-danger btn-sm" onclick="eliminarDoctor(${doc.id})">Eliminar</button>
            </td>
          </tr>
        `;
      });
    } catch (error) {
      console.error("Error al obtener doctores:", error);
    }
  };

  formulario.addEventListener("submit", async e => {
    e.preventDefault();
    const nuevoDoctor = {
      nombre: document.getElementById("nombre").value,
      especialidad: document.getElementById("especialidad").value,
      telefono: document.getElementById("telefono").value,
      correo: document.getElementById("correo").value,
    };

    await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevoDoctor)
    });

    formulario.reset();
    obtenerDoctores();
  });

  window.eliminarDoctor = async (id) => {
    await fetch(`${url}/${id}`, { method: "DELETE" });
    obtenerDoctores();
  };

  window.editarDoctor = (doctor) => {
    document.getElementById("edit-id").value = doctor.id;
    document.getElementById("edit-nombre").value = doctor.nombre;
    document.getElementById("edit-especialidad").value = doctor.especialidad;
    document.getElementById("edit-telefono").value = doctor.telefono;
    document.getElementById("edit-correo").value = doctor.correo;
    const modal = new bootstrap.Modal(document.getElementById("modalEditar"));
    modal.show();
  };

  formEditar.addEventListener("submit", async e => {
    e.preventDefault();
    const id = document.getElementById("edit-id").value;

    const doctorEditado = {
      nombre: document.getElementById("edit-nombre").value,
      especialidad: document.getElementById("edit-especialidad").value,
      telefono: document.getElementById("edit-telefono").value,
      correo: document.getElementById("edit-correo").value,
    };

    await fetch(`${url}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(doctorEditado)
    });

    obtenerDoctores();
    bootstrap.Modal.getInstance(document.getElementById("modalEditar")).hide();
  });

  buscar.addEventListener("input", async () => {
    const texto = buscar.value.toLowerCase();
    const res = await fetch(url);
    const doctores = await res.json();

    const filtrados = doctores.filter(d =>
      d.nombre.toLowerCase().includes(texto)
    );

    tabla.innerHTML = "";
    filtrados.forEach(doc => {
      tabla.innerHTML += `
        <tr>
          <td>${doc.nombre}</td>
          <td>${doc.especialidad}</td>
          <td>${doc.telefono}</td>
          <td>${doc.correo}</td>
          <td>
            <button class="btn btn-warning btn-sm" onclick='editarDoctor(${JSON.stringify(doc)})'>Editar</button>
            <button class="btn btn-danger btn-sm" onclick="eliminarDoctor(${doc.id})">Eliminar</button>
          </td>
        </tr>
      `;
    });
  });

  obtenerDoctores();
});
