let currentPage = 0;
let size = 5;
let sortBy = "id";

function fetchBooks(page = 0) {
  fetch(`/api/books?page=${page}&size=${size}&sortBy=${sortBy}`)
    .then(res => res.json())
    .then(data => {
      renderBooks(data.content);
      renderPagination(data.totalPages, page);
    });
}

function renderBooks(books) {
  const table = document.getElementById("bookTable");
  table.innerHTML = "";
  books.forEach(book => {
    table.innerHTML += `
      <tr>
        <td>${book.id}</td>
        <td>${book.title}</td>
        <td>${book.author}</td>
        <td>
          <a href="add-book.html?id=${book.id}" class="btn btn-sm btn-warning">Edit</a>
          <button onclick="deleteBook(${book.id})" class="btn btn-sm btn-danger">Delete</button>
        </td>
      </tr>`;
  });
}

function renderPagination(totalPages, currentPage) {
  const pagination = document.getElementById("pagination");
  pagination.innerHTML = "";
  for (let i = 0; i < totalPages; i++) {
    pagination.innerHTML += `
      <li class="page-item ${i === currentPage ? 'active' : ''}">
        <button class="page-link" onclick="fetchBooks(${i})">${i + 1}</button>
      </li>`;
  }
}

function searchBooks() {
  const title = document.getElementById("searchTitle").value;
  const author = document.getElementById("searchAuthor").value;
  let url = "";

  if (title) {
    url = `/api/books/search/title?title=${title}&page=0&size=${size}`;
  } else if (author) {
    url = `/api/books/search/author?author=${author}&page=0&size=${size}`;
  } else {
    return fetchBooks(0);
  }

  fetch(url)
    .then(res => res.json())
    .then(data => {
      renderBooks(data.content);
      renderPagination(data.totalPages, 0);
    });
}

function deleteBook(id) {
  if (confirm("Are you sure you want to delete this book?")) {
    fetch(`/api/books/${id}`, { method: "DELETE" })
      .then(() => fetchBooks(currentPage));
  }
}

function loadBookForm() {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (id) {
    fetch(`/api/books?page=0&size=100`)
      .then(res => res.json())
      .then(data => {
        const book = data.content.find(b => b.id == id);
        if (book) {
          document.getElementById("formTitle").innerText = "Update Book";
          document.getElementById("bookId").value = book.id;
          document.getElementById("title").value = book.title;
          document.getElementById("author").value = book.author;
        }
      });
  }

  document.getElementById("bookForm").onsubmit = function(e) {
    e.preventDefault();
    const book = {
      title: document.getElementById("title").value,
      author: document.getElementById("author").value
    };

    const bookId = document.getElementById("bookId").value;
    let url = "/api/books";
    let method = "POST";

    if (bookId) {
      url += `/${bookId}`;
      method = "PUT";
    }

    fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(book)
    }).then(() => window.location.href = "index.html");
  };
}

// Auto-fetch on load
if (window.location.pathname.endsWith("index.html") || window.location.pathname === "/") {
  fetchBooks();
}
