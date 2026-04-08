const apiUrl = '/api/immobilien';

async function loadImmobilien() {
    const res = await fetch(apiUrl);
    const data = await res.json();
    const tbody = document.querySelector('#immobilienTable tbody');
    tbody.innerHTML = '';
    data.forEach(i => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${i.id}</td>
            <td>${i.bezeichnung}</td>
            <td>${i.strasse}</td>
            <td>${i.hausnummer}</td>
            <td>${i.plz}</td>
            <td>${i.ort}</td>
            <td>
                <button onclick="showEditForm(${i.id}, '${i.bezeichnung}', '${i.strasse}', '${i.hausnummer}', '${i.plz}', '${i.ort}')">Bearbeiten</button>
                <button onclick="deleteImmobilie(${i.id})">Löschen</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

function addImmobilie() {
    const bezeichnung = document.getElementById('newBezeichnung').value;
    const strasse = document.getElementById('newStrasse').value;
    const hausnummer = document.getElementById('newHausnummer').value;
    const plz = document.getElementById('newPlz').value;
    const ort = document.getElementById('newOrt').value;

    fetch('/api/immobilien', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            bezeichnung,
            strasse,
            hausnummer,
            plz,
            ort
        })
    }).then(() => loadImmobilien());
}

async function deleteImmobilie(id) {
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
    loadImmobilien();
}

function showEditForm(id, bezeichnung, strasse, hausnummer, plz, ort) {
    document.getElementById('editForm').style.display = 'block';

    document.getElementById('editId').value = id;
    document.getElementById('editBezeichnung').value = bezeichnung;
    document.getElementById('editStrasse').value = strasse;
    document.getElementById('editHausnummer').value = hausnummer;
    document.getElementById('editPlz').value = plz;
    document.getElementById('editOrt').value = ort;
}

function hideEditForm() {
    document.getElementById('editForm').style.display = 'none';
}

function submitEdit() {
    const id = document.getElementById('editId').value;

    fetch(`/api/immobilien/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            bezeichnung: document.getElementById('editBezeichnung').value,
            strasse: document.getElementById('editStrasse').value,
            hausnummer: document.getElementById('editHausnummer').value,
            plz: document.getElementById('editPlz').value,
            ort: document.getElementById('editOrt').value
        })
    }).then(() => {
        hideEditForm();
        loadImmobilien();
    });
}

loadImmobilien();