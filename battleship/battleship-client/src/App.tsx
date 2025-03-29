import React from "react"
import "./App.css"
import Home from "./resources/pages/home/Home.tsx"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"

import HowToPlay from "./resources/pages/how-to-play/HowToPlay.tsx"
import Game from "./resources/pages/game/Game.tsx"

function App() {
	return (
		<>
			<Router>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/game" element={<Game />} />
					<Route
						path="/how-to-play"
						element={<HowToPlay setTutorial={null} />}
					/>
				</Routes>
			</Router>
		</>
	)
}

export default App
