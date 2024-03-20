---

excalidraw-plugin: parsed
tags: [excalidraw]

---
==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠==

# Text Elements
Basic client backend modules interactions ^gufUH8dk

Calendar ^DL70I8Vo

Kanban board ^Dgwhj1Er

Github integration ^5KoADGpT

Markdown parser ^5ioU7CPo

Cards ^xLinI9vA

Insert tasks in board ^YDj5ochv

Saving user data in JSON ^Bd1M2N1F

save/load ^1i1e41jS

issues ^HVacyTPJ

tasks in calendar ^cZVIPJPH

Save/load ^94FitYbW

issues/PRs ^ochGRLRL

sprints/time limited cards ^B8ivB2Ec

save/load (maybe ical) ^KK5jt2z1

Saveable ^f4iQbcPX

Saveable ^uQ5mbhT6

Saveable ^NiXPwSHi

This interface defines saving
 and loading to/from json ^LqOr3Jp5

KanbanInsertable ^uT4nElga

This interface defines how a class instance 
should be displayed in a kanban board ^jqKFnZtx

KanbanInsertable ^nG06HjcX

a) * Use library -> commonmark-java
b) Write own parser

 ^iapQbqZV

String [md] -> String [HTML] ^5bpnvEpl

!!! ^LMkdI0Yu

For (de)serialization use library Jackson ^gVysBEDo

KanbanDisplay ^DwnQiQqm

Add boards as events ^Tr2PPgEV

Workspace - directory ^LlobDVY9

worskpace_name.ical ^pWo05RHu

Kanban board 1 
board.json ^vkOv11er

List1 <Card1, 2...>
List2 <Card6, 9...> ^w6hQkGGs

Kanban board 2...
3...
.... ^Hk1Mhhqz

analogicznie ^o53Ja8Wy

Card1: title..., card2: title... ^7bGVTXUd

Structure of a workspace ^RGIgvsWl

board_id - dir
|_ title.board [json]
|_ title.cards [json]
 ^ywvmpKpd

list 1 {
    - cardId
    - cardId
    ...
}
list 2
... ^GgkjjVjc

card1 {
}
card2 {
}
 ^BHu0GKU3

app
|_workspaces
  |_workspace1
  | |_workspace1.ical
  | |_board1
  |   |_board [json]
  |   |_cards [json]
  | |_board2
  | |_...
  |_workspace2
  |_... ^eNnYaZ1Z

%%
# Drawing
```json
{
	"type": "excalidraw",
	"version": 2,
	"source": "https://excalidraw.com",
	"elements": [
		{
			"type": "rectangle",
			"version": 108,
			"versionNonce": 972737133,
			"isDeleted": false,
			"id": "oaqArhHf1zB6EdL0yfGfx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1856.4217600388474,
			"y": 904.3308896734242,
			"strokeColor": "#e03131",
			"backgroundColor": "#ffc9c9",
			"width": 413.9342572643284,
			"height": 180.74923603345485,
			"seed": 1589174381,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 79,
			"versionNonce": 264183523,
			"isDeleted": false,
			"id": "gufUH8dk",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 530.342831,
			"y": -283.379647,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 570.9166870117188,
			"height": 35,
			"seed": 139493761,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 28,
			"fontFamily": 1,
			"text": "Basic client backend modules interactions",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Basic client backend modules interactions",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 288,
			"versionNonce": 644936909,
			"isDeleted": false,
			"id": "DL70I8Vo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 888.3333333333333,
			"y": 469.1111111111111,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 85.06666564941406,
			"height": 25,
			"seed": 1848333057,
			"groupIds": [
				"nW60NBtP6QtAsTUNEmMeW"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Calendar",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Calendar",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 329,
			"versionNonce": 32923152,
			"isDeleted": false,
			"id": "wiVOR7ibe-og9u7-vKvW8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 871.3333333333333,
			"y": 454.1111111111111,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 120,
			"height": 51,
			"seed": 1498265025,
			"groupIds": [
				"nW60NBtP6QtAsTUNEmMeW"
			],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "gJlgWzzC2nPOuQhMmxNur",
					"type": "arrow"
				},
				{
					"id": "cmGKrY0yp0tOKCZ1J9RPh",
					"type": "arrow"
				},
				{
					"id": "ktU-5SMc1c55UkWdlAH8N",
					"type": "arrow"
				},
				{
					"id": "M1e7VOYQvLFQo9k9tRT0i",
					"type": "arrow"
				}
			],
			"updated": 1710889378565,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 206,
			"versionNonce": 1265816365,
			"isDeleted": false,
			"id": "Dgwhj1Er",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1379.7777777777776,
			"y": 337.33333333333326,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 132.3333282470703,
			"height": 25,
			"seed": 1150645743,
			"groupIds": [
				"GPuTpG3n0eRhYWeCd45BJ"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Kanban board",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Kanban board",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 233,
			"versionNonce": 1575574883,
			"isDeleted": false,
			"id": "WxyvQButunSW0vKEg_EAH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1367.7777777777776,
			"y": 321.33333333333326,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 161,
			"height": 53,
			"seed": 511628865,
			"groupIds": [
				"GPuTpG3n0eRhYWeCd45BJ"
			],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "QFDCOWaMb31cKyZTLljQX",
					"type": "arrow"
				},
				{
					"id": "S4SZ14yLUkHl0jLq-jj-W",
					"type": "arrow"
				},
				{
					"id": "cAdUlbnkw1jZwt5W9zn1R",
					"type": "arrow"
				},
				{
					"id": "gJlgWzzC2nPOuQhMmxNur",
					"type": "arrow"
				},
				{
					"id": "AoWYZMydHx1rOfwyZUjNX",
					"type": "arrow"
				},
				{
					"id": "ktU-5SMc1c55UkWdlAH8N",
					"type": "arrow"
				}
			],
			"updated": 1710888215757,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 335,
			"versionNonce": 293742093,
			"isDeleted": false,
			"id": "5KoADGpT",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1989.7070268888885,
			"y": 618.7562330000001,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 176.88333129882812,
			"height": 25,
			"seed": 1111248591,
			"groupIds": [
				"GsHlg7O2nvKRixuoNB-p7"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710887030371,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Github integration",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Github integration",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 352,
			"versionNonce": 1686671437,
			"isDeleted": false,
			"id": "xXntfin9uQCGiOqnTaYGg",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1971.7070268888885,
			"y": 604.7562330000001,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 208.18282330293096,
			"height": 59,
			"seed": 1957989249,
			"groupIds": [
				"GsHlg7O2nvKRixuoNB-p7"
			],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "gr8vvCrTUeIIkBgxcjYyA",
					"type": "arrow"
				},
				{
					"id": "cAdUlbnkw1jZwt5W9zn1R",
					"type": "arrow"
				}
			],
			"updated": 1710887034674,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 260,
			"versionNonce": 608426000,
			"isDeleted": false,
			"id": "5ioU7CPo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1620.1944436190474,
			"y": 1135.5079365079366,
			"strokeColor": "#f08c00",
			"backgroundColor": "transparent",
			"width": 163.1666717529297,
			"height": 25,
			"seed": 1626139009,
			"groupIds": [
				"Q0FVD_OoaGGNzngbRZeA-"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710890430951,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Markdown parser",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Markdown parser",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 278,
			"versionNonce": 345075952,
			"isDeleted": false,
			"id": "KN1ii9Km6KJn71_W5txdj",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1602.1944436190474,
			"y": 1120.5079365079366,
			"strokeColor": "#f08c00",
			"backgroundColor": "transparent",
			"width": 200,
			"height": 55,
			"seed": 1284977455,
			"groupIds": [
				"Q0FVD_OoaGGNzngbRZeA-"
			],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "XPkOiPzSSbrR_74INiRjH",
					"type": "arrow"
				},
				{
					"id": "iFZdsrwbOm5PcKyt1p6GZ",
					"type": "arrow"
				}
			],
			"updated": 1710890430951,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 100,
			"versionNonce": 1127117123,
			"isDeleted": false,
			"id": "xLinI9vA",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1372.493333333333,
			"y": 777.8826855555556,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 57.06666564941406,
			"height": 25,
			"seed": 1660698561,
			"groupIds": [
				"Bvd1F6J8nLeG4a7lKETIR"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888039446,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Cards",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Cards",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 143,
			"versionNonce": 879550480,
			"isDeleted": false,
			"id": "U99Tq66nFC5JD8MWzoWrO",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1356.6085653333332,
			"y": 765.8826855555556,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 95,
			"height": 49,
			"seed": 1737172911,
			"groupIds": [
				"Bvd1F6J8nLeG4a7lKETIR"
			],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "QFDCOWaMb31cKyZTLljQX",
					"type": "arrow"
				},
				{
					"id": "4qkGamIjWKJgXOLZ4KHF7",
					"type": "arrow"
				},
				{
					"id": "gr8vvCrTUeIIkBgxcjYyA",
					"type": "arrow"
				}
			],
			"updated": 1710889378565,
			"link": null,
			"locked": false
		},
		{
			"type": "arrow",
			"version": 507,
			"versionNonce": 660427949,
			"isDeleted": false,
			"id": "QFDCOWaMb31cKyZTLljQX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1386.5991651940403,
			"y": 754.8826855555556,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 75.0823269601924,
			"height": 371.5493522222223,
			"seed": 1822958607,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "YDj5ochv"
				}
			],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "U99Tq66nFC5JD8MWzoWrO",
				"focus": -0.47059608666993785,
				"gap": 11
			},
			"endBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"focus": -0.23967732678634154,
				"gap": 9
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					75.0823269601924,
					-371.5493522222223
				]
			]
		},
		{
			"type": "text",
			"version": 33,
			"versionNonce": 122771619,
			"isDeleted": false,
			"id": "YDj5ochv",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1337.6319973753084,
			"y": 559.1080094444444,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 173.01666259765625,
			"height": 20,
			"seed": 1926784449,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Insert tasks in board",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "QFDCOWaMb31cKyZTLljQX",
			"originalText": "Insert tasks in board",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 370,
			"versionNonce": 43795984,
			"isDeleted": false,
			"id": "Bd1M2N1F",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 577.7777777777776,
			"y": -125.61111111111126,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 256.7833251953125,
			"height": 25,
			"seed": 546385633,
			"groupIds": [
				"a-w8SDyMNdqaeQKINHORt"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710890269012,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Saving user data in JSON",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Saving user data in JSON",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 389,
			"versionNonce": 2021480464,
			"isDeleted": false,
			"id": "ThApxk_ajJro4s6oewMMJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 558.7777777777776,
			"y": -143.11111111111126,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 288,
			"height": 54,
			"seed": 2138087937,
			"groupIds": [
				"a-w8SDyMNdqaeQKINHORt"
			],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "4qkGamIjWKJgXOLZ4KHF7",
					"type": "arrow"
				},
				{
					"id": "S4SZ14yLUkHl0jLq-jj-W",
					"type": "arrow"
				},
				{
					"id": "cmGKrY0yp0tOKCZ1J9RPh",
					"type": "arrow"
				},
				{
					"id": "jZONdshMOEiz_Nh7ccBT5",
					"type": "arrow"
				}
			],
			"updated": 1710890265972,
			"link": null,
			"locked": false
		},
		{
			"type": "arrow",
			"version": 823,
			"versionNonce": 2126878669,
			"isDeleted": false,
			"id": "4qkGamIjWKJgXOLZ4KHF7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1371.9622483512871,
			"y": 755.5493522222221,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 664.7781424679885,
			"height": 833.3271299999999,
			"seed": 716474447,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "1i1e41jS"
				}
			],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "U99Tq66nFC5JD8MWzoWrO",
				"focus": -0.3444277820598756,
				"gap": 10.333333333333485
			},
			"endBinding": {
				"elementId": "ThApxk_ajJro4s6oewMMJ",
				"focus": 0.2575667742736183,
				"gap": 11.333333333333428
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-166.56542295446138,
					-456.58109825396787
				],
				[
					-664.7781424679885,
					-833.3271299999999
				]
			]
		},
		{
			"type": "text",
			"version": 20,
			"versionNonce": 1809471363,
			"isDeleted": false,
			"id": "1i1e41jS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1053.0166651407878,
			"y": 190,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 77.30000305175781,
			"height": 20,
			"seed": 1771293665,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "save/load",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "4qkGamIjWKJgXOLZ4KHF7",
			"originalText": "save/load",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 677,
			"versionNonce": 722140845,
			"isDeleted": false,
			"id": "gr8vvCrTUeIIkBgxcjYyA",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1963.9292491111105,
			"y": 629.4940713243809,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 504.54335179978807,
			"height": 133.09897150293546,
			"seed": 1814660545,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "HVacyTPJ"
				}
			],
			"updated": 1710887034674,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "xXntfin9uQCGiOqnTaYGg",
				"gap": 7.777777777777828,
				"focus": 0.39200938469903457
			},
			"endBinding": {
				"elementId": "U99Tq66nFC5JD8MWzoWrO",
				"focus": -0.08340875147223757,
				"gap": 7.777331977989206
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-254.30283413281018,
					24.34126894767485
				],
				[
					-504.54335179978807,
					133.09897150293546
				]
			]
		},
		{
			"type": "text",
			"version": 16,
			"versionNonce": 1581936419,
			"isDeleted": false,
			"id": "HVacyTPJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 855.1972213321262,
			"y": 59.44444444444446,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 47.38333511352539,
			"height": 20,
			"seed": 1414699599,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "issues",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "gr8vvCrTUeIIkBgxcjYyA",
			"originalText": "issues",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 683,
			"versionNonce": 67871248,
			"isDeleted": false,
			"id": "zB2hADeyrsDoNoArQVi78",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1329.2124759401026,
			"y": 776.1801601814611,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 381.803442125626,
			"height": 254.7357564271208,
			"seed": 1264957999,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "cZVIPJPH"
				}
			],
			"updated": 1710889378565,
			"link": null,
			"locked": false,
			"startBinding": null,
			"endBinding": null,
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-381.803442125626,
					-254.7357564271208
				]
			]
		},
		{
			"type": "text",
			"version": 25,
			"versionNonce": 639013571,
			"isDeleted": false,
			"id": "cZVIPJPH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 801.1999969482422,
			"y": -159.99999999999994,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 137.60000610351562,
			"height": 20,
			"seed": 661935041,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "tasks in calendar",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "zB2hADeyrsDoNoArQVi78",
			"originalText": "tasks in calendar",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 969,
			"versionNonce": 1431882061,
			"isDeleted": false,
			"id": "S4SZ14yLUkHl0jLq-jj-W",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 858.3466424404803,
			"y": -99.29643167351395,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 521.034330097367,
			"height": 404.85198722906955,
			"seed": 1453828751,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "94FitYbW"
				}
			],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ThApxk_ajJro4s6oewMMJ",
				"focus": -0.4580199680683287,
				"gap": 11.568864662702708
			},
			"endBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"focus": -0.42983586120118544,
				"gap": 15.777777777777658
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					331.2565321626946,
					107.86786024494245
				],
				[
					521.034330097367,
					404.85198722906955
				]
			]
		},
		{
			"type": "text",
			"version": 25,
			"versionNonce": 1617755651,
			"isDeleted": false,
			"id": "94FitYbW",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1150.4191917540538,
			"y": -1.4285714285715017,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 78.3499984741211,
			"height": 20,
			"seed": 2067025089,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Save/load",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "S4SZ14yLUkHl0jLq-jj-W",
			"originalText": "Save/load",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 697,
			"versionNonce": 670081293,
			"isDeleted": false,
			"id": "cAdUlbnkw1jZwt5W9zn1R",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2024.502749306261,
			"y": 592.4447839497442,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 488.60035256820265,
			"height": 237.29986543886147,
			"seed": 808981487,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "ochGRLRL"
				}
			],
			"updated": 1710887034675,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "xXntfin9uQCGiOqnTaYGg",
				"gap": 12.311449050255874,
				"focus": 0.13510447957896377
			},
			"endBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"focus": -0.4596647873438393,
				"gap": 7.1246189602807135
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-253.8083048618164,
					-146.88922839418854
				],
				[
					-488.60035256820265,
					-237.29986543886147
				]
			]
		},
		{
			"type": "text",
			"version": 21,
			"versionNonce": 650397091,
			"isDeleted": false,
			"id": "ochGRLRL",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1076.6944444444446,
			"y": 105,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 85.5,
			"height": 20,
			"seed": 1553309409,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992650,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "issues/PRs",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "cAdUlbnkw1jZwt5W9zn1R",
			"originalText": "issues/PRs",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 825,
			"versionNonce": 1314841283,
			"isDeleted": false,
			"id": "gJlgWzzC2nPOuQhMmxNur",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1356.5224075518502,
			"y": 369.6241614789778,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 352.2438501504448,
			"height": 131.72018630010996,
			"seed": 1697117857,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "B8ivB2Ec"
				}
			],
			"updated": 1710890422118,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"focus": 0.4953986330619206,
				"gap": 11.255370225927436
			},
			"endBinding": {
				"elementId": "wiVOR7ibe-og9u7-vKvW8",
				"focus": 0.9193406382257628,
				"gap": 12.945224068072093
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": null,
			"points": [
				[
					0,
					0
				],
				[
					-169.5957487353321,
					114.15916233538593
				],
				[
					-352.2438501504448,
					131.72018630010996
				]
			]
		},
		{
			"type": "text",
			"version": 40,
			"versionNonce": 1971838701,
			"isDeleted": false,
			"id": "B8ivB2Ec",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1000.9888899061416,
			"y": -30.555555555555515,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 199.13333129882812,
			"height": 20,
			"seed": 551721057,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710890422118,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "sprints/time limited cards",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "gJlgWzzC2nPOuQhMmxNur",
			"originalText": "sprints/time limited cards",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 788,
			"versionNonce": 235354829,
			"isDeleted": false,
			"id": "cmGKrY0yp0tOKCZ1J9RPh",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 646.9540300886299,
			"y": -80.00000000000014,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 258.59333589572884,
			"height": 522.2222222222223,
			"seed": 1455243311,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "KK5jt2z1"
				}
			],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ThApxk_ajJro4s6oewMMJ",
				"focus": 0.4071196658362033,
				"gap": 9.111111111111114
			},
			"endBinding": {
				"elementId": "wiVOR7ibe-og9u7-vKvW8",
				"focus": 0.1475706018520141,
				"gap": 11.888888888888914
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					33.918985784385995,
					304.12698412698444
				],
				[
					258.59333589572884,
					522.2222222222223
				]
			]
		},
		{
			"type": "text",
			"version": 41,
			"versionNonce": 624452739,
			"isDeleted": false,
			"id": "KK5jt2z1",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 812.0972196790908,
			"y": 388.8888888888889,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 176.9166717529297,
			"height": 20,
			"seed": 1683861473,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "save/load (maybe ical)",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "cmGKrY0yp0tOKCZ1J9RPh",
			"originalText": "save/load (maybe ical)",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 38,
			"versionNonce": 877680941,
			"isDeleted": false,
			"id": "f4iQbcPX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 788.4758268568308,
			"y": 451.18083147900234,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 69.26667022705078,
			"height": 20,
			"seed": 1054860557,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "GFSH6aStfvCSxc4xC-GW2",
					"type": "arrow"
				}
			],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Saveable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Saveable",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 48,
			"versionNonce": 979295267,
			"isDeleted": false,
			"id": "uQ5mbhT6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1435.0112220782719,
			"y": 293.0658058991352,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 69.26667022705078,
			"height": 20,
			"seed": 1977850381,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Saveable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Saveable",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 49,
			"versionNonce": 1151773581,
			"isDeleted": false,
			"id": "NiXPwSHi",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1361.7110189739692,
			"y": 825.593550035394,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 69.26667022705078,
			"height": 20,
			"seed": 1135451565,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Saveable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Saveable",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 24,
			"versionNonce": 1058365379,
			"isDeleted": false,
			"id": "GFSH6aStfvCSxc4xC-GW2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 780.3103733546754,
			"y": 458.676937970616,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 153.9601303085143,
			"height": 26.679011750211828,
			"seed": 946217901,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "f4iQbcPX",
				"focus": -0.3070037283404996,
				"gap": 8.165453502155401
			},
			"endBinding": {
				"elementId": "LqOr3Jp5",
				"focus": 0.6126384980202877,
				"gap": 12.802210729396165
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-153.9601303085143,
					-26.679011750211828
				]
			]
		},
		{
			"type": "text",
			"version": 96,
			"versionNonce": 1866707437,
			"isDeleted": false,
			"id": "LqOr3Jp5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 463.4971090453137,
			"y": 379.195715491008,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 228.5500030517578,
			"height": 40,
			"seed": 1093364259,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "GFSH6aStfvCSxc4xC-GW2",
					"type": "arrow"
				}
			],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "This interface defines saving\n and loading to/from json",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "This interface defines saving\n and loading to/from json",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 39,
			"versionNonce": 1935376525,
			"isDeleted": false,
			"id": "uT4nElga",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2063.3888893863837,
			"y": 573.8048675840598,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 135.48333740234375,
			"height": 20,
			"seed": 1203846349,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "YdXyJGu4laMEAahsj3rpO",
					"type": "arrow"
				}
			],
			"updated": 1710889205058,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "KanbanInsertable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "KanbanInsertable",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 179,
			"versionNonce": 173298189,
			"isDeleted": false,
			"id": "YdXyJGu4laMEAahsj3rpO",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2209.998907877279,
			"y": 573.7616209707999,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 223.562217797642,
			"height": 32.962448422304305,
			"seed": 783187181,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710889205084,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "uT4nElga",
				"focus": 0.07930987764330581,
				"gap": 11.126765132593391
			},
			"endBinding": {
				"elementId": "jqKFnZtx",
				"focus": 0.6266733438342617,
				"gap": 11.996761642750698
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					223.562217797642,
					-32.962448422304305
				]
			]
		},
		{
			"type": "text",
			"version": 93,
			"versionNonce": 2010942211,
			"isDeleted": false,
			"id": "jqKFnZtx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2445.557887317672,
			"y": 521.7962543784616,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 354.8999938964844,
			"height": 40,
			"seed": 348261283,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "YdXyJGu4laMEAahsj3rpO",
					"type": "arrow"
				}
			],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "This interface defines how a class instance \nshould be displayed in a kanban board",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "This interface defines how a class instance \nshould be displayed in a kanban board",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 44,
			"versionNonce": 1783093933,
			"isDeleted": false,
			"id": "nG06HjcX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1295.494352004205,
			"y": 856.3044142781223,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 135.48333740234375,
			"height": 20,
			"seed": 1445594403,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "KanbanInsertable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "KanbanInsertable",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 170,
			"versionNonce": 590478605,
			"isDeleted": false,
			"id": "iapQbqZV",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1625.4611147010482,
			"y": 1185.935717228801,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 285.79998779296875,
			"height": 80,
			"seed": 1984553891,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "a) * Use library -> commonmark-java\nb) Write own parser\n\n",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "a) * Use library -> commonmark-java\nb) Write own parser\n\n",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 66,
			"versionNonce": 1589765699,
			"isDeleted": false,
			"id": "5bpnvEpl",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1813.7035717599533,
			"y": 1138.007937177308,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 230.06666564941406,
			"height": 20,
			"seed": 1853644771,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "String [md] -> String [HTML]",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "String [md] -> String [HTML]",
			"lineHeight": 1.25
		},
		{
			"type": "image",
			"version": 81,
			"versionNonce": 1463147981,
			"isDeleted": false,
			"id": "6diwBfrfINqWEchGu1EIs",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1873.9689268965353,
			"y": 923.1576352980628,
			"strokeColor": "transparent",
			"backgroundColor": "transparent",
			"width": 378.8399241034646,
			"height": 143.0957459801819,
			"seed": 270081315,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"status": "saved",
			"fileId": "bf574204e8c03c36b1d0061c64c39172847ac458",
			"scale": [
				1,
				1
			]
		},
		{
			"type": "text",
			"version": 42,
			"versionNonce": 1173975427,
			"isDeleted": false,
			"id": "LMkdI0Yu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2229.5874524216274,
			"y": 859.330889933507,
			"strokeColor": "#e03131",
			"backgroundColor": "#ffc9c9",
			"width": 31.950000762939453,
			"height": 45,
			"seed": 435412707,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 36,
			"fontFamily": 1,
			"text": "!!!",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "!!!",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 84,
			"versionNonce": 1169333293,
			"isDeleted": false,
			"id": "gVysBEDo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1223.7518812741073,
			"y": -66.15771684915958,
			"strokeColor": "#e03131",
			"backgroundColor": "#ffc9c9",
			"width": 397.1499938964844,
			"height": 25,
			"seed": 2109121837,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710886992651,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "For (de)serialization use library Jackson",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "For (de)serialization use library Jackson",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 28,
			"versionNonce": 1438324240,
			"isDeleted": false,
			"id": "rcapocY12rFDrei6zBW88",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1688.601474701823,
			"y": 145.80824703339295,
			"strokeColor": "#f08c00",
			"backgroundColor": "#ffec99",
			"width": 170.48701299999993,
			"height": 74.17244949460473,
			"seed": 309456899,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "XPkOiPzSSbrR_74INiRjH",
					"type": "arrow"
				},
				{
					"type": "text",
					"id": "DwnQiQqm"
				},
				{
					"id": "iFZdsrwbOm5PcKyt1p6GZ",
					"type": "arrow"
				},
				{
					"id": "AoWYZMydHx1rOfwyZUjNX",
					"type": "arrow"
				}
			],
			"updated": 1710889142539,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 16,
			"versionNonce": 170601712,
			"isDeleted": false,
			"id": "DwnQiQqm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1719.286646851237,
			"y": 172.8944717806953,
			"strokeColor": "#f08c00",
			"backgroundColor": "transparent",
			"width": 109.11666870117188,
			"height": 20,
			"seed": 1671272045,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710889139448,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "KanbanDisplay",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "rcapocY12rFDrei6zBW88",
			"originalText": "KanbanDisplay",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 71,
			"versionNonce": 1359010563,
			"isDeleted": false,
			"id": "iFZdsrwbOm5PcKyt1p6GZ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1734.9498661884563,
			"y": 1109.1975244515359,
			"strokeColor": "#f08c00",
			"backgroundColor": "transparent",
			"width": 139.01906102290263,
			"height": 880.2768394993475,
			"seed": 1388753677,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710890438302,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "KN1ii9Km6KJn71_W5txdj",
				"focus": 0.19215309175039832,
				"gap": 11.310412056400764
			},
			"endBinding": {
				"elementId": "rcapocY12rFDrei6zBW88",
				"focus": -0.2177066090459306,
				"gap": 8.939988424190688
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					139.01906102290263,
					-452.7283324086658
				],
				[
					66.61489780703073,
					-880.2768394993475
				]
			]
		},
		{
			"type": "arrow",
			"version": 28,
			"versionNonce": 512427760,
			"isDeleted": false,
			"id": "AoWYZMydHx1rOfwyZUjNX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1539.3979162244,
			"y": 317.0570112983104,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 135.95484706208913,
			"height": 90.77658099599088,
			"seed": 1978896387,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710889133808,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"focus": 0.37623786661947856,
				"gap": 11.448767216283386
			},
			"endBinding": {
				"elementId": "rcapocY12rFDrei6zBW88",
				"focus": 0.23804506304456907,
				"gap": 14.670207898803937
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					135.95484706208913,
					-90.77658099599088
				]
			]
		},
		{
			"type": "arrow",
			"version": 69,
			"versionNonce": 102251597,
			"isDeleted": false,
			"id": "ktU-5SMc1c55UkWdlAH8N",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1355.9972138050757,
			"y": 330.7610319228476,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 355.6858602030136,
			"height": 125.33139609111089,
			"seed": 2104411715,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "Tr2PPgEV"
				}
			],
			"updated": 1710888242008,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"focus": 0.8255416161662447,
				"gap": 11.780563972701884
			},
			"endBinding": {
				"elementId": "wiVOR7ibe-og9u7-vKvW8",
				"focus": 0.3206007455905355,
				"gap": 8.97802026872887
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-224.5520846632403,
					41.777132030370296
				],
				[
					-355.6858602030136,
					125.33139609111089
				]
			]
		},
		{
			"type": "text",
			"version": 31,
			"versionNonce": 1948384141,
			"isDeleted": false,
			"id": "Tr2PPgEV",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1043.7284619665425,
			"y": 362.5381639532179,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 175.43333435058594,
			"height": 20,
			"seed": 1365121763,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888241310,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Add boards as events",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "ktU-5SMc1c55UkWdlAH8N",
			"originalText": "Add boards as events",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 274,
			"versionNonce": 1083991280,
			"isDeleted": false,
			"id": "D-mETqQc_4izbG65DrqAb",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 252.3492346869574,
			"y": 698.3892452906048,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 609.1428920200893,
			"height": 64.00006975446422,
			"seed": 1318216208,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "LlobDVY9"
				}
			],
			"updated": 1710888730876,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 228,
			"versionNonce": 1805797104,
			"isDeleted": false,
			"id": "LlobDVY9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 450.2707783532521,
			"y": 717.8892801678369,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 213.2998046875,
			"height": 25,
			"seed": 1986288,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888730876,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Workspace - directory",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "D-mETqQc_4izbG65DrqAb",
			"originalText": "Workspace - directory",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 328,
			"versionNonce": 1067182320,
			"isDeleted": false,
			"id": "3pHRWBXycgbDhiMdE04H-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 254.06349424331916,
			"y": 767.532137310694,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 609.1428920200893,
			"height": 64.00006975446422,
			"seed": 573300752,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "pWo05RHu"
				}
			],
			"updated": 1710888730876,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 313,
			"versionNonce": 1745963760,
			"isDeleted": false,
			"id": "pWo05RHu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 459.6650229560005,
			"y": 787.0321721879261,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 197.93983459472656,
			"height": 25,
			"seed": 1607818768,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888730876,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "worskpace_name.ical",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "3pHRWBXycgbDhiMdE04H-",
			"originalText": "worskpace_name.ical",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 175,
			"versionNonce": 261050096,
			"isDeleted": false,
			"id": "ELwdJI29_p4c27pvl5nXE",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 565.7777102031406,
			"y": 850.3891929747562,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 297.1428571428571,
			"height": 297.1428571428572,
			"seed": 1953901584,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "o53Ja8Wy"
				}
			],
			"updated": 1710888730876,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 94,
			"versionNonce": 2133450979,
			"isDeleted": false,
			"id": "vkOv11er",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 298.63490537613154,
			"y": 880.3891929747565,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 157.75,
			"height": 50,
			"seed": 1227266576,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710889547413,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Kanban board 1 \nboard.json",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Kanban board 1 \nboard.json",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 154,
			"versionNonce": 464074768,
			"isDeleted": false,
			"id": "8CknqwEXHmoKcn14usaci",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 289.4920845637246,
			"y": 929.8177876548159,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 236.57143729073653,
			"height": 78.85716029575883,
			"seed": 1625910288,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1710889420249,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 91,
			"versionNonce": 1891129072,
			"isDeleted": false,
			"id": "w6hQkGGs",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 320.9206196618459,
			"y": 948.960621546185,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 187.3198699951172,
			"height": 50,
			"seed": 1147063824,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888730876,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "List1 <Card1, 2...>\nList2 <Card6, 9...>",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "List1 <Card1, 2...>\nList2 <Card6, 9...>",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 177,
			"versionNonce": 667917453,
			"isDeleted": false,
			"id": "IMOW5Z7hy_641BSRSZPE6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 272.4196107238973,
			"y": 1166.360897634358,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 268.3105432761026,
			"height": 55.509119192567596,
			"seed": 623673539,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "7bGVTXUd"
				}
			],
			"updated": 1710889641479,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 67,
			"versionNonce": 2107276528,
			"isDeleted": false,
			"id": "Hk1Mhhqz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 298.063476804703,
			"y": 1020.3891929747565,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 173.0198974609375,
			"height": 75,
			"seed": 572700400,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888730876,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Kanban board 2...\n3...\n....",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Kanban board 2...\n3...\n....",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 69,
			"versionNonce": 287137520,
			"isDeleted": false,
			"id": "CBo7D5oInidlMMJiUAJf_",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 565.7777099849329,
			"y": 1166.3608978320312,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 297.428676,
			"height": 55.509119000000055,
			"seed": 1416019917,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1710888730876,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 54,
			"versionNonce": 1890054384,
			"isDeleted": false,
			"id": "o53Ja8Wy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 657.8991876026942,
			"y": 986.4606215461847,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 112.89990234375,
			"height": 25,
			"seed": 1699964944,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888730876,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "analogicznie",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "ELwdJI29_p4c27pvl5nXE",
			"originalText": "analogicznie",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 104,
			"versionNonce": 1109820141,
			"isDeleted": false,
			"id": "7bGVTXUd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 297.29988083606963,
			"y": 1184.1154572306418,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 218.5500030517578,
			"height": 20,
			"seed": 1652965027,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710889641479,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Card1: title..., card2: title...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "IMOW5Z7hy_641BSRSZPE6",
			"originalText": "Card1: title..., card2: title...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 114,
			"versionNonce": 905183472,
			"isDeleted": false,
			"id": "TvSIJI3y9FIW0h9B9Rio4",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 246.63490537613154,
			"y": 688.1034699697342,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 626.2857491629462,
			"height": 542.8571864536831,
			"seed": 132776976,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "M1e7VOYQvLFQo9k9tRT0i",
					"type": "arrow"
				}
			],
			"updated": 1710888730876,
			"link": null,
			"locked": false
		},
		{
			"type": "arrow",
			"version": 175,
			"versionNonce": 194558480,
			"isDeleted": false,
			"id": "M1e7VOYQvLFQo9k9tRT0i",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 660.3195287265588,
			"y": 687.1034699697343,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 222.54426474452703,
			"height": 174.99993024553578,
			"seed": 824643088,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "RGIgvsWl"
				}
			],
			"updated": 1710888749600,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "TvSIJI3y9FIW0h9B9Rio4",
				"focus": -0.3734886623871498,
				"gap": 1
			},
			"endBinding": {
				"elementId": "wiVOR7ibe-og9u7-vKvW8",
				"focus": 0.0773522202215245,
				"gap": 6.992428613087441
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					222.54426474452703,
					-174.99993024553578
				]
			]
		},
		{
			"type": "text",
			"version": 57,
			"versionNonce": 1129643536,
			"isDeleted": false,
			"id": "RGIgvsWl",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 691.6917282374942,
			"y": 574.6035048469664,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 159.79986572265625,
			"height": 50,
			"seed": 1588203760,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710888748980,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Structure of a \nworkspace",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "M1e7VOYQvLFQo9k9tRT0i",
			"originalText": "Structure of a workspace",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 93,
			"versionNonce": 1765684976,
			"isDeleted": false,
			"id": "TMukKt9Sb5xxcT8Pp-esF",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 266.0635073222811,
			"y": 853.91300685822,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 274.66664632161445,
			"height": 160.00000000000014,
			"seed": 1870484208,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1710889408567,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 46,
			"versionNonce": 840371309,
			"isDeleted": false,
			"id": "EsKZoZI1bW5_qnuxvCzlR",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 256.920637,
			"y": 842.3691585340885,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 300.000044,
			"height": 388.5914974659115,
			"seed": 872051085,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1710889636428,
			"link": null,
			"locked": false
		},
		{
			"type": "freedraw",
			"version": 10,
			"versionNonce": 1383353581,
			"isDeleted": false,
			"id": "3CWbkI2R_QgLY1jgB1Yl7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 257.4821324416161,
			"y": 907.3172389640343,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 120.3015580691042,
			"height": 40.59255026871608,
			"seed": 1342489485,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710889551905,
			"link": null,
			"locked": false,
			"points": [
				[
					0,
					0
				],
				[
					-2.9521854740884805,
					0
				],
				[
					-13.28483463339802,
					2.214139105566346
				],
				[
					-33.95013295201716,
					6.642417316698925
				],
				[
					-58.30566311324685,
					12.546788264875886
				],
				[
					-88.56556422265339,
					23.617483792707503
				],
				[
					-115.13523348944943,
					36.16427205758339
				],
				[
					-120.3015580691042,
					40.59255026871608
				],
				[
					-120.3015580691042,
					40.59255026871608
				]
			],
			"lastCommittedPoint": null,
			"simulatePressure": true,
			"pressures": []
		},
		{
			"type": "freedraw",
			"version": 23,
			"versionNonce": 2001610499,
			"isDeleted": false,
			"id": "heFz465290wwT_W4ZzNKe",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 135.7044816354677,
			"y": 930.1966763882198,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 44.28278211132671,
			"height": 35.426225689061425,
			"seed": 1213052333,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710889552804,
			"link": null,
			"locked": false,
			"points": [
				[
					0,
					0
				],
				[
					-0.7380463685221343,
					0
				],
				[
					-3.690231842610558,
					3.690231842610615
				],
				[
					-6.642417316699039,
					8.118510053743194
				],
				[
					-11.070695527831688,
					14.022881001920155
				],
				[
					-12.546788264875929,
					17.713112844530656
				],
				[
					-13.284834633398034,
					17.713112844530656
				],
				[
					-12.546788264875929,
					18.451159213052847
				],
				[
					-11.070695527831688,
					19.189205581574925
				],
				[
					-8.85655642226537,
					19.927251950097002
				],
				[
					-6.642417316699039,
					20.665298318619193
				],
				[
					-5.166324579654827,
					20.665298318619193
				],
				[
					-2.9521854740884805,
					20.665298318619193
				],
				[
					0.7380463685220775,
					22.141391055663348
				],
				[
					5.16632457965477,
					23.617483792707503
				],
				[
					14.760927370442232,
					26.56966926679604
				],
				[
					22.141391055663348,
					29.521854740884464
				],
				[
					26.569669266795984,
					32.47404021497289
				],
				[
					29.521854740884464,
					34.688179320539234
				],
				[
					30.25990110940654,
					35.426225689061425
				],
				[
					30.997947477928676,
					35.426225689061425
				],
				[
					30.997947477928676,
					35.426225689061425
				]
			],
			"lastCommittedPoint": null,
			"simulatePressure": true,
			"pressures": []
		},
		{
			"type": "text",
			"version": 104,
			"versionNonce": 1841105315,
			"isDeleted": false,
			"id": "ywvmpKpd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 80.3510039963093,
			"y": 972.2653193939801,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 160.56666564941406,
			"height": 80,
			"seed": 1803321923,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "RgmDnArJ29p_MKeJTkG7M",
					"type": "arrow"
				}
			],
			"updated": 1710889913611,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "board_id - dir\n|_ title.board [json]\n|_ title.cards [json]\n",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "board_id - dir\n|_ title.board [json]\n|_ title.cards [json]\n",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 64,
			"versionNonce": 426574061,
			"isDeleted": false,
			"id": "GgkjjVjc",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -15.595023911565193,
			"y": 1147.9203551022426,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 99.08333587646484,
			"height": 140,
			"seed": 1703099373,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "RgmDnArJ29p_MKeJTkG7M",
					"type": "arrow"
				}
			],
			"updated": 1710889821963,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "list 1 {\n    - cardId\n    - cardId\n    ...\n}\nlist 2\n...",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "list 1 {\n    - cardId\n    - cardId\n    ...\n}\nlist 2\n...",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 13,
			"versionNonce": 1833303373,
			"isDeleted": false,
			"id": "5JjN1DJClmTWsIrhMt8O3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 179.24921737827225,
			"y": 1033.5231679813155,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 0.7380463685220775,
			"height": 86.35142511708682,
			"seed": 185346253,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710889792192,
			"link": null,
			"locked": false,
			"startBinding": null,
			"endBinding": null,
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-0.7380463685220775,
					86.35142511708682
				]
			]
		},
		{
			"type": "text",
			"version": 21,
			"versionNonce": 108364387,
			"isDeleted": false,
			"id": "BHu0GKU3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 148.25126990034357,
			"y": 1142.754030522588,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 63.25,
			"height": 100,
			"seed": 841917763,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1710889802818,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "card1 {\n}\ncard2 {\n}\n",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "card1 {\n}\ncard2 {\n}\n",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 88,
			"versionNonce": 290577731,
			"isDeleted": false,
			"id": "RgmDnArJ29p_MKeJTkG7M",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 70.01835483699973,
			"y": 1004.0016762564077,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 56.8295703762026,
			"height": 126.20556600130442,
			"seed": 1191450925,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710889913611,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ywvmpKpd",
				"focus": 0.811089035863225,
				"gap": 10.332649159309561
			},
			"endBinding": {
				"elementId": "GgkjjVjc",
				"focus": -0.575746322804007,
				"gap": 17.713112844530542
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-42.80668937428247,
					40.592187252739336
				],
				[
					-56.8295703762026,
					126.20556600130442
				]
			]
		},
		{
			"type": "text",
			"version": 225,
			"versionNonce": 1682812944,
			"isDeleted": false,
			"id": "eNnYaZ1Z",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -14.856977543043229,
			"y": 691.0696529870562,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 196.875,
			"height": 211.2,
			"seed": 997250733,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "jZONdshMOEiz_Nh7ccBT5",
					"type": "arrow"
				}
			],
			"updated": 1710890272453,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 3,
			"text": "app\n|_workspaces\n  |_workspace1\n  | |_workspace1.ical\n  | |_board1\n  |   |_board [json]\n  |   |_cards [json]\n  | |_board2\n  | |_...\n  |_workspace2\n  |_...",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "app\n|_workspaces\n  |_workspace1\n  | |_workspace1.ical\n  | |_board1\n  |   |_board [json]\n  |   |_cards [json]\n  | |_board2\n  | |_...\n  |_workspace2\n  |_...",
			"lineHeight": 1.2
		},
		{
			"type": "arrow",
			"version": 148,
			"versionNonce": 1503796752,
			"isDeleted": false,
			"id": "jZONdshMOEiz_Nh7ccBT5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 570.0634717184395,
			"y": -78.08724236866806,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 418.66663614908845,
			"height": 746.6666412353514,
			"seed": 285742832,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1710890278692,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ThApxk_ajJro4s6oewMMJ",
				"focus": 0.699976629886111,
				"gap": 11.023868742443199
			},
			"endBinding": {
				"elementId": "eNnYaZ1Z",
				"focus": -0.02540911339750134,
				"gap": 22.490254120372867
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-418.66663614908845,
					746.6666412353514
				]
			]
		}
	],
	"appState": {
		"gridSize": null,
		"viewBackgroundColor": "#ffffff"
	},
	"files": {
		"bf574204e8c03c36b1d0061c64c39172847ac458": {
			"mimeType": "image/png",
			"id": "bf574204e8c03c36b1d0061c64c39172847ac458",
			"dataURL": "data:image/png;base64,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",
			"created": 1710886741479,
			"lastRetrieved": 1710886741479
		}
	}
}
```
%%