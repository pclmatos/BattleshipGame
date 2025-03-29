import React from "react"
import Board from "../board/Board.tsx"
import { Button } from "@mui/material"

import "../home/Home.css"
import { useLocation, useNavigate } from "react-router-dom"

function Game(): any {
	const location = useLocation()
	let navigate = useNavigate()
	const { playerName } = location.state || {}

	if (!playerName) {
		return (
			<div>
				To view this page you first need to set your name!
				<Button className="btn-home" onClick={() => navigate("/")}>
					Go back
				</Button>
			</div>
		)
	}

	return (
		<div className="App">
			<div className="BoardContainer">
				<Board clickable={true} />
			</div>
			<div className="BoardContainer">
				<Board clickable={false} />
			</div>
		</div>
	)
}

export default Game
