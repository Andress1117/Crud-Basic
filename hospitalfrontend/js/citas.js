const URL = 'http://localhost:8080/citas';
const tabla = document.getElementById('tabla');
const formulario = document.getElementById('formulario');
const buscar = document.getElementById('buscar');
const modalEditar = new bootstrap.Modal(document.getElementById('modalEditar'));
const formEditar = document.getElementById('formEditar');

document.addEventListener('DOMContentLoaded', () => {
  obtenerCitas();
  cargarPacientes();
  cargarDoctores();
});

formulario.addEventListener('submit', guardarCita);
formEditar.addEventListener('submit', actualizarCita);
buscar.addEventListener('input', obtenerCitas);

function obtenerCitas() {
  fetch(URL)
    .then(res => res.json())
    .then(citas => {
      tabla.innerHTML = '';
      citas.forEach(c => {
        if (!c.paciente.toLowerCase().includes(buscar.value.toLowerCase())) return;
        tabla.innerHTML += `
          <tr>
            <td>${c.paciente}</td>
            <td>${c.doctor}</td>
            <td>${c.fecha}</td>
            <td>${c.hora}</td>
            <td>${c.motivo}</td> <!-- MOTIVO ANTES DE ACCIONES -->
            <td>
              <button class="btn btn-warning btn-sm" onclick='abrirModal(${JSON.stringify(c)})'>Editar</button>
              <button class="btn btn-danger btn-sm" onclick='eliminarCita(${c.id})'>Eliminar</button>
            </td>
          </tr>
        `;
      });
    });
}

function guardarCita(e) {
  e.preventDefault();
  const cita = {
    paciente: document.getElementById('paciente').value,
    doctor: document.getElementById('doctor').value,
    fecha: document.getElementById('fecha').value,
    hora: document.getElementById('hora').value,
    motivo: document.getElementById('motivo').value
  };

  fetch(URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(cita)
  }).then(() => {
    formulario.reset();
    obtenerCitas();
  });
}

function eliminarCita(id) {
  fetch(`${URL}/${id}`, { method: 'DELETE' })
    .then(() => obtenerCitas());
}

function abrirModal(c) {
  document.getElementById('edit-id').value = c.id;
  document.getElementById('edit-paciente').value = c.paciente;
  document.getElementById('edit-doctor').value = c.doctor;
  document.getElementById('edit-fecha').value = c.fecha;
  document.getElementById('edit-hora').value = c.hora;
  document.getElementById('edit-motivo').value = c.motivo;
  modalEditar.show();
}

function actualizarCita(e) {
  e.preventDefault();
  const id = document.getElementById('edit-id').value;
  const cita = {
    paciente: document.getElementById('edit-paciente').value,
    doctor: document.getElementById('edit-doctor').value,
    fecha: document.getElementById('edit-fecha').value,
    hora: document.getElementById('edit-hora').value,
    motivo: document.getElementById('edit-motivo').value
  };

  fetch(`${URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(cita)
  }).then(() => {
    modalEditar.hide();
    obtenerCitas();
  });
}

function cargarPacientes() {
  fetch('http://localhost:8080/pacientes')
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('paciente');
      const selectEdit = document.getElementById('edit-paciente');
      select.innerHTML = '<option value="">Seleccione paciente</option>';
      selectEdit.innerHTML = '<option value="">Seleccione paciente</option>';
      data.forEach(p => {
        select.innerHTML += `<option value="${p.nombre}">${p.nombre}</option>`;
        selectEdit.innerHTML += `<option value="${p.nombre}">${p.nombre}</option>`;
      });
    });
}

function cargarDoctores() {
  fetch('http://localhost:8080/doctores')
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('doctor');
      const selectEdit = document.getElementById('edit-doctor');
      select.innerHTML = '<option value="">Seleccione doctor</option>';
      selectEdit.innerHTML = '<option value="">Seleccione doctor</option>';
      data.forEach(d => {
        select.innerHTML += `<option value="${d.nombre}">${d.nombre}</option>`;
        selectEdit.innerHTML += `<option value="${d.nombre}">${d.nombre}</option>`;
      });
    });
}
