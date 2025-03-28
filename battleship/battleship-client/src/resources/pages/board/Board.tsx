import React, { JSX, useState } from "react"
import "./Board.css"

const grid_size = 10

type Cell = "water" | "ship" | "hit" | "miss"

function Board(props: { clickable: boolean }): JSX.Element {
	// State to track clicked cells
	const [board, setBoard] = useState<Cell[][]>(
		Array(grid_size)
			.fill(null)
			.map(() => Array(grid_size).fill("water"))
	)
	// console.log(board)
	const [clickedCells, setClickedCells] = useState<Set<string>>(new Set())

	const fromNumberToLetter = (num: number): string => {
		return String.fromCharCode(65 + num)
	}

	const fromLetterToNumber = (str: string): number => {
		return Number.parseInt(str)
	}

	// Generate rows (1-10) and columns (A-J)
	const rows: number[] = Array.from({ length: grid_size }, (_, i) => i + 1)
	const columns: string[] = Array.from({ length: grid_size }, (_, i) =>
		fromNumberToLetter(i)
	)

	// Handle cell click
	const handleCoordinateClick = (row: number, column: string): void => {
		const coordinate = `${row}${column}`
		console.log(coordinate)
		setClickedCells((prev) => new Set(prev).add(coordinate))
	}

	// Generate grid cells
	const cells = Array.from({ length: grid_size * grid_size }, (_, i) => {
		const row = Math.floor(i / grid_size) + 1 // Calculate row number
		const column = fromNumberToLetter(i % grid_size) // Calculate column letter
		const coordinate = `${row}${column}`
		const isClicked = clickedCells.has(coordinate)

		return (
			<div
				key={i}
				className={`coordinate ${isClicked ? "clicked" : ""} ${
					props.clickable ? "clickable" : ""
				}`}
				onClick={
					props.clickable ? () => handleCoordinateClick(row, column) : undefined
				}
			></div>
		)
	})

	return (
		<div className="container">
			<div className="BoardWrapper">
				{/* Row Labels */}
				<div className="rowLabels">
					{rows.map((row, i) => (
						<div key={i} className="rowLabel">
							{row}
						</div>
					))}
				</div>
				{/* Column Labels */}
				<div className="columnLabels">
					{columns.map((col, i) => (
						<div key={i} className="columnLabel">
							{col}
						</div>
					))}
				</div>
				{/* Board Grid */}
				<div className="Board">{cells}</div>
			</div>
		</div>
	)
}

export default Board
