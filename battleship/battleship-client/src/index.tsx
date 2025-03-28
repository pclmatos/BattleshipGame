import React from "react"
import ReactDOM from "react-dom/client"
import "./index.css"
import App from "./App.tsx"
import reportWebVitals from "./reportWebVitals.ts"

const headElement = document.getElementById("head")

const metaElement = document.createElement("meta")
metaElement.name = "viewport"
metaElement.content = "width=device-width, initial-scale=1.0"
headElement?.appendChild(metaElement)

const rootElement = document.getElementById("root")
if (!rootElement) {
	throw new Error("Root element not found")
}
const root = ReactDOM.createRoot(rootElement)
root.render(
	<React.StrictMode>
		<App />
	</React.StrictMode>
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals(console.log)
