import * as React from "react"
import { Box, Button } from "@mui/material"
import { useNavigate } from "react-router-dom"

import battleship from "../../images/battleship_logo.png"

function Home(): any {
	let navigate = useNavigate()

	return (
		<Box
			sx={{
				height: "100vh", // Full viewport height
				display: "flex", // Flexbox for centering
				justifyContent: "center", // Center horizontally
				alignItems: "center", // Center vertically
			}}
		>
			<Box
				sx={{
					width: 500,
					height: 600,
					backgroundColor: "white",
					display: "flex",
					flexDirection: "column",
					justifyContent: "center",
					borderRadius: 2,
					alignItems: "center",
					justifyItems: "center",
					gap: 5,
					opacity: 0,
					animation: "fadeIn 2s ease-in forwards",
					boxShadow: 1,
				}}
			>
				<img src={battleship} alt="battleship" width={450} />
				<Button
					sx={{
						border: 1,
						width: 200,
						height: 100,
						fontSize: 20,
					}}
					onClick={() => navigate("/game")}
				>
					Jogar
				</Button>
				<Button
					sx={{
						border: 1,
						width: 200,
						height: 100,
						fontSize: 20,
					}}
					onClick={() => navigate("/how-to-play")}
				>
					Como Jogar?
				</Button>
			</Box>
		</Box>
	)
}

export default Home
