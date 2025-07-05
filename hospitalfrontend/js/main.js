const URL = 'http://localhost:8080/pacientes';
const tabla = document.getElementById('tabla');
const formulario = document.getElementById('formulario');
const buscar = document.getElementById('buscar');

// MODAL
const modalEditar = new bootstrap.Modal(document.getElementById('modalEditar'));
const formEditar = document.getElementById('formEditar');

function obtenerPacientes() {
  fetch(URL)
    .then(res => res.json())
    .then(pacientes => {
      tabla.innerHTML = '';
      pacientes.forEach(p => {
        if (!p.nombre.toLowerCase().includes(buscar.value.toLowerCase())) return;
        tabla.innerHTML += `
          <tr>
            <td>${p.nombre}</td>
            <td>${p.edad}</td>
            <td>${p.genero}</td>
            <td>${p.diagnostico}</td>
            <td>
              <button class="btn btn-warning btn-sm" onclick='abrirModal(${JSON.stringify(p)})'>Editar</button>
              <button class="btn btn-danger btn-sm" onclick="eliminarPaciente(${p.id})">Eliminar</button>
            </td>
          </tr>
        `;
      });
    });
}

formulario.addEventListener('submit', e => {
  e.preventDefault();
  const paciente = {
    nombre: document.getElementById('nombre').value.trim(),
    edad: parseInt(document.getElementById('edad').value),
    genero: document.getElementById('genero').value,
    diagnostico: document.getElementById('diagnostico').value.trim()
  };
  fetch(URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(paciente)
  }).then(() => {
    formulario.reset();
    obtenerPacientes();
  });
});

function eliminarPaciente(id) {
  fetch(`${URL}/${id}`, { method: 'DELETE' }).then(() => obtenerPacientes());
}

function abrirModal(p) {
  document.getElementById('edit-id').value = p.id;
  document.getElementById('edit-nombre').value = p.nombre;
  document.getElementById('edit-edad').value = p.edad;
  document.getElementById('edit-genero').value = p.genero;
  document.getElementById('edit-diagnostico').value = p.diagnostico;
  modalEditar.show();
}

formEditar.addEventListener('submit', e => {
  e.preventDefault();
  const id = document.getElementById('edit-id').value;
  const paciente = {
    nombre: document.getElementById('edit-nombre').value.trim(),
    edad: parseInt(document.getElementById('edit-edad').value),
    genero: document.getElementById('edit-genero').value,
    diagnostico: document.getElementById('edit-diagnostico').value.trim()
  };
  fetch(`${URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(paciente)
  }).then(() => {
    modalEditar.hide();
    obtenerPacientes();
  });
});

buscar.addEventListener('input', obtenerPacientes);

document.addEventListener('DOMContentLoaded', obtenerPacientes);
