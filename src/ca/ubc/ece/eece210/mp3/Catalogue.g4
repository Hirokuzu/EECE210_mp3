grammar Catalogue;

// Lexer Rules

START_GENRE: 	'<genre>' ;
END_GENRE:		'</genre>' ;
START_ALBUM:	'<album>' ;
END_ALBUM:		'</album>' ;
START_NAME:		'<name>' ;
END_NAME:		'</name>' ;
START_PERF:		'<performer>' ;
END_PERF:		'</performer>' ;
START_SONG:		'<song>' ;
END_SONG:		'</song>' ;
TEXT:			~[\t\n<>]+ ;
WS:				[\t\n]+ -> skip ;

// Parser Rules

root: 		catalogue EOF ;
name:		START_NAME TEXT END_NAME ;
catalogue: 	( genre | album )* ;
genre:		START_GENRE (name) ( genre | album )* END_GENRE ;
album:		START_ALBUM (name) (performer) (song)+ END_ALBUM ;
performer:	START_PERF TEXT END_PERF ;
song:		START_SONG TEXT END_SONG ;