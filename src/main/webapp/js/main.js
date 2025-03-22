console.log("main.js loaded");

window.addEventListener('DOMContentLoaded', () => {
    console.log("DOM ready");

    const menu = document.getElementById('menu');

    if (!menu) {
        console.error("Menu not found!");
        return;
    }

    menu.addEventListener('click', function (e) {
        let target = e.target;
		console.log("Raw clicked element:", target);
        // If user clicks inside the <a>, walk up to find the <a> element
        while (target && target.tagName !== 'A') {
            target = target.parentElement;
        }

        if (target && target.tagName === 'A') {
            e.preventDefault();

            const action = target.dataset.action;
            console.log("Clicked link for action:", action);

            if (action) {
                loadPage(action);
            } else {
                console.warn("No data-action found on clicked link.");
            }
        }
    });

    // Load default content
    loadPage('home');
});

function loadPage(action) {
    console.log("Loading:", action);

    fetch('Controller?action=' + action)
        .then(response => {
            if (!response.ok) throw new Error("Fetch failed with status " + response.status);
            return response.text();
        })
        .then(html => {
            const content = document.getElementById('content');
            if (content) {
                content.innerHTML = html;
            } else {
                console.error("Content container not found!");
            }
        })
        .catch(error => {
            console.error("Fetch error:", error);
            const content = document.getElementById('content');
            if (content) {
                content.innerHTML = "<p>Error loading content.</p>";
            }
        });
}
