const events = [
    {
        id: 1,
        name: "Tech Conference",
        description: "A conference about the latest in tech.",
        date: "2024-07-01",
        time: "10:00",
        location: "New York",
        category: "Tech",
        rsvps: 0
    },
    {
        id: 2,
        name: "Music Festival",
        description: "A fun-filled music festival.",
        date: "2024-08-15",
        time: "18:00",
        location: "Los Angeles",
        category: "Music",
        rsvps: 0
    }
];

document.addEventListener("DOMContentLoaded", () => {
    displayEvents(events);

    document.getElementById("event-form").addEventListener("submit", createEvent);
    document.getElementById("category").addEventListener("change", filterEvents);
    document.getElementById("location").addEventListener("input", filterEvents);
    document.getElementById("date").addEventListener("change", filterEvents);
});

function displayEvents(events) {
    const eventsContainer = document.getElementById("events");
    eventsContainer.innerHTML = "";

    events.forEach(event => {
        const eventCard = document.createElement("div");
        eventCard.className = "event-card";

        eventCard.innerHTML = `
            <h3>${event.name}</h3>
            <p>${event.description}</p>
            <p>Date: ${event.date}</p>
            <p>Time: ${event.time}</p>
            <p>Location: ${event.location}</p>
            <p>Category: ${event.category}</p>
            <p>RSVPs: ${event.rsvps}</p>
            <button onclick="rsvpEvent(${event.id})">RSVP</button>
        `;

        eventsContainer.appendChild(eventCard);
    });
}

function filterEvents() {
    const category = document.getElementById("category").value;
    const location = document.getElementById("location").value.toLowerCase();
    const date = document.getElementById("date").value;

    const filteredEvents = events.filter(event => {
        return (
            (category === "" || event.category === category) &&
            (location === "" || event.location.toLowerCase().includes(location)) &&
            (date === "" || event.date === date)
        );
    });

    displayEvents(filteredEvents);
}

function createEvent(event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;
    const date = document.getElementById("event-date").value;
    const time = document.getElementById("time").value;
    const location = document.getElementById("event-location").value;
    const category = document.getElementById("event-category").value;

    const newEvent = {
        id: events.length + 1,
        name,
        description,
        date,
        time,
        location,
        category,
        rsvps: 0
    };

    events.push(newEvent);
    displayEvents(events);
    document.getElementById("event-form").reset();
}

function rsvpEvent(eventId) {
    const event = events.find(event => event.id === eventId);
    event.rsvps += 1;
    displayEvents(events);
}
