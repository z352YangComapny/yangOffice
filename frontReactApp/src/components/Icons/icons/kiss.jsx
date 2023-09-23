import React from 'react';

function Kiss(props) {
	const fill = props.fill || 'currentColor';
	const secondaryfill = props.secondaryfill || fill;
	const strokewidth = props.strokewidth || 1;
	const width = props.width || '100%';
	const height = props.height || '100%';

	return (
		<svg height={height} width={width} viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
	<g>
		<path d="M24,46A22,22,0,1,1,46,24,22,22,0,0,1,24,46Z" fill="#ffd764"/>
		<path d="M17,19a1,1,0,0,0-1,1,2,2,0,0,1-4,0,1,1,0,0,0-2,0,4,4,0,0,0,8,0A1,1,0,0,0,17,19Z" fill="#363636"/>
		<path d="M37,19a1,1,0,0,0-1,1,2,2,0,0,1-4,0,1,1,0,0,0-2,0,4,4,0,0,0,8,0A1,1,0,0,0,37,19Z" fill="#363636"/>
		<path d="M25,39H23a1,1,0,0,1,0-2h2a1.5,1.5,0,0,0,1.5-1.5,1.481,1.481,0,0,0-.309-.9l-.458-.6.458-.6a1.481,1.481,0,0,0,.309-.9A1.5,1.5,0,0,0,25,31H23a1,1,0,0,1,0-2h2a3.5,3.5,0,0,1,3.5,3.5,3.416,3.416,0,0,1-.344,1.5,3.416,3.416,0,0,1,.344,1.5A3.5,3.5,0,0,1,25,39Z" fill="#363636"/>
		<path d="M34.879,36.121a3,3,0,1,1,4.168-4.316c.025.024.05.049.074.074l.879.878.879-.878A3,3,0,0,1,45.2,36.047c-.024.025-.049.05-.074.074L40,41.243Z" fill="#ff7163"/>
	</g>
</svg>
	);
};

export default Kiss;