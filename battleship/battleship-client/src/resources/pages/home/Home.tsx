import React, { useState } from "react"
import { Box, Button, Container, Input, TextField } from "@mui/material"

import "./Home.css"
import battleship from "../../images/battleship_logo.png"
import HowToPlay from "../how-to-play/HowToPlay.tsx"
import { useNavigate } from "react-router-dom"

function Home(): any {
	let navigate = useNavigate()

	const [showNameField, setShowNameField] = useState<boolean>(false)
	const [showTutorial, setShowTutorial] = useState<boolean>(false)
	const [playerName, setPlayerName] = useState<string | null>(null)
	const [error, setError] = useState<boolean>(false)

	return (
		<Container
			sx={{
				height: "100vh", // Full viewport height
				display: "flex", // Flexbox for centering
				justifyContent: "center", // Center horizontally
				alignItems: "center", // Center vertically
			}}
		>
			<Box
				sx={{
					height: 600,
					width: 500,
					backgroundColor: "white",
					display: "flex",
					flexDirection: "column",
					borderRadius: 5,
					alignItems: "center",
					gap: 5,
					animation: "fadeIn 1s ease forwards",
				}}
			>
				<img
					src={battleship}
					alt="battleship"
					style={{ paddingTop: 20 }}
					width={450}
				/>
				<Box />
				{!showNameField && !showTutorial ? (
					<>
						<Button className="btn-home" onClick={() => setShowNameField(true)}>
							Jogar
						</Button>
						<Button className="btn-home" onClick={() => setShowTutorial(true)}>
							Tutorial
						</Button>
					</>
				) : showTutorial ? (
					<HowToPlay setTutorial={setShowTutorial} />
				) : (
					<Box
						component={"form"}
						sx={{
							height: 600,
							width: 500,
							backgroundColor: "white",
							display: "flex",
							flexDirection: "column",
							alignItems: "center",
							borderRadius: 5,
							gap: 5,
							animation: "fadeIn 1s ease forwards",
						}}
						autoComplete="off"
						noValidate
					>
						<TextField
							type="text"
							// placeholder="Nome do jogador"
							label="Nome do jogador"
							variant="outlined"
							value={playerName || ""}
							onChange={(nome) => {
								setError(false)
								setPlayerName(nome.target.value)
							}}
							error={error}
							helperText={error ? "Por favor, insira um nome." : ""}
						/>
						<Button
							className="btn-home"
							onClick={() => {
								if (!playerName) {
									setError(true)
								} else {
									navigate("/game", { state: { playerName } })
								}
							}}
						>
							Jogar
						</Button>
						<Button
							className="btn-home"
							onClick={() => {
								setShowNameField(false)
							}}
						>
							Voltar
						</Button>
					</Box>
				)}
			</Box>
		</Container>
	)
}

export default Home
