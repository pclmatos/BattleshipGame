import React from "react"
import "./App.css"
import Board from "./resources/pages/board/Board.tsx"
import Home from "./resources/pages/home/Home.tsx"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import HowToPlay from "./resources/pages/how-to-play/HowToPlay.tsx"

function App() {
	return (
		<>
			<Router>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route
						path="/game"
						element={
							<div className="App">
								<div className="BoardContainer">
									<Board clickable={true} />
								</div>
								<div className="BoardContainer">
									<Board clickable={false} />
								</div>
							</div>
						}
					/>
					<Route path="/how-to-play" element={<HowToPlay />} />
				</Routes>
			</Router>
		</>
	)
}

export default App
