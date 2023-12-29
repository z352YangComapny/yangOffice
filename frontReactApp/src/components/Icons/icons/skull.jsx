import React from 'react';

function Skull(props) {
	const fill = props.fill || 'currentColor';
	const secondaryfill = props.secondaryfill || fill;
	const strokewidth = props.strokewidth || 1;
	const width = props.width || '100%';
	const height = props.height || '100%';

	return (
		<svg height={height} width={width} viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
	<g>
		<rect height="12" width="18" fill="#c8c8c8" rx="4" x="15" y="34"/>
		<path d="M43,21A19,19,0,1,0,7.305,30.042a3.125,3.125,0,0,1,.178,2.837,4.916,4.916,0,0,0-.4,3.021,5.028,5.028,0,0,0,4.063,4.028A4.97,4.97,0,0,0,15.977,38H32.023a4.97,4.97,0,0,0,4.833,1.928A5.028,5.028,0,0,0,40.919,35.9a4.922,4.922,0,0,0-.4-3.023,3.1,3.1,0,0,1,.167-2.814A18.789,18.789,0,0,0,43,21Z" fill="#e3e3e3"/>
		<path d="M15.5,27A4.5,4.5,0,1,1,20,22.5,4.5,4.5,0,0,1,15.5,27Z" fill="#363636"/>
		<path d="M32.5,27A4.5,4.5,0,1,1,37,22.5,4.5,4.5,0,0,1,32.5,27Z" fill="#363636"/>
		<path d="M18,42a1,1,0,0,1-1-1V35a1,1,0,0,1,2,0v6A1,1,0,0,1,18,42Z" fill="#363636"/>
		<path d="M24,42a1,1,0,0,1-1-1V35a1,1,0,0,1,2,0v6A1,1,0,0,1,24,42Z" fill="#363636"/>
		<path d="M30,42a1,1,0,0,1-1-1V35a1,1,0,0,1,2,0v6A1,1,0,0,1,30,42Z" fill="#363636"/>
		<path d="M32,39H16a1,1,0,0,1,0-2H32a1,1,0,0,1,0,2Z" fill="#363636"/>
		<path d="M26.9,30.553l-2-4a1.042,1.042,0,0,0-1.79,0l-2,4A1,1,0,0,0,22,32h4a1,1,0,0,0,.895-1.447Z" fill="#363636"/>
	</g>
</svg>
	);
};

export default Skull;