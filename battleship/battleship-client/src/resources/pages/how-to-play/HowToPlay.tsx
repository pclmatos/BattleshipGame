import { JSX } from "@emotion/react/jsx-runtime"
import { Button } from "@mui/material"
import React from "react"

import "../home/Home.css"

function HowToPlay(props: { setTutorial: Function | null }): JSX.Element {
	return (
		<>
			<Button
				className="btn-home"
				onClick={() => props.setTutorial && props.setTutorial(false)}
			>
				Voltar
			</Button>
		</>
	)
}

export default HowToPlay
