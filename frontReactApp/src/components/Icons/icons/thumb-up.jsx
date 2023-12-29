import React from 'react';

function ThumbUp(props) {
	const fill = props.fill || 'currentColor';
	const secondaryfill = props.secondaryfill || fill;
	const strokewidth = props.strokewidth || 1;
	const width = props.width || '100%';
	const height = props.height || '100%';

	return (
		<svg height={height} width={width} viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
	<g>
		<rect height="24" width="10" fill="#2a4b55" rx="2" x="2" y="21"/>
		<path d="M43.935,26.5A4,4,0,0,0,42,19H24V10c0-7.909-4.8-8-5-8a1,1,0,0,0-.961.725l-6,21A.983.983,0,0,0,12,24V41a1,1,0,0,0,.293.707C12.427,41.842,15.67,45,23,45H40a3,3,0,0,0,1.922-5.3,3.5,3.5,0,0,0,.929-5.789A4,4,0,0,0,43.935,26.5Z" fill="#ffd764"/>
	</g>
</svg>
	);
};

export default ThumbUp;