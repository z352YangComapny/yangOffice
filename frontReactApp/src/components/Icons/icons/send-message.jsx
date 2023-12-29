import React from 'react';

function SendMessage(props) {
	const fill = props.fill || 'currentColor';
	const secondaryfill = props.secondaryfill || fill;
	const strokewidth = props.strokewidth || 1;
	const width = props.width || '100%';
	const height = props.height || '100%';

	return (
		<svg height={height} width={width} viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg">
	<g fill={fill}>
		<path d="M30.707,1.293a1,1,0,0,0-1.013-.245l-28,9a1,1,0,0,0-.2,1.816L11.4,17.643,21,11l-6.643,9.6L20.136,30.5A1,1,0,0,0,21,31a.977.977,0,0,0,.108-.006,1,1,0,0,0,.844-.688l9-28A1,1,0,0,0,30.707,1.293Z" fill={fill}/>
	</g>
</svg>
	);
};

export default SendMessage;