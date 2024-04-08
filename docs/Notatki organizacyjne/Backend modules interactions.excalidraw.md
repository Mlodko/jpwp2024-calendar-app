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

For (de)serialization use library GSON ^gVysBEDo

KanbanDisplay ^DwnQiQqm

Add boards as events ^Tr2PPgEV

Workspace - directory ^LlobDVY9

worskpace_name.json ^pWo05RHu

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

# Embedded files
bf574204e8c03c36b1d0061c64c39172847ac458: [[Pasted Image 20240401205425_205.png]]

%%
# Drawing
```json
{
	"type": "excalidraw",
	"version": 2,
	"source": "https://github.com/zsviczian/obsidian-excalidraw-plugin/releases/tag/2.1.0",
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
			"version": 80,
			"versionNonce": 540089096,
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
			"width": 570.92236328125,
			"height": 35,
			"seed": 139493761,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 28,
			"fontFamily": 1,
			"text": "Basic client backend modules interactions",
			"rawText": "Basic client backend modules interactions",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Basic client backend modules interactions",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 289,
			"versionNonce": 189266296,
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
			"width": 85.07991027832031,
			"height": 25,
			"seed": 1848333057,
			"groupIds": [
				"nW60NBtP6QtAsTUNEmMeW"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Calendar",
			"rawText": "Calendar",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 207,
			"versionNonce": 677935624,
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
			"width": 132.33985900878906,
			"height": 25,
			"seed": 1150645743,
			"groupIds": [
				"GPuTpG3n0eRhYWeCd45BJ"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Kanban board",
			"rawText": "Kanban board",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 336,
			"versionNonce": 1274677880,
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
			"width": 176.87979125976562,
			"height": 25,
			"seed": 1111248591,
			"groupIds": [
				"GsHlg7O2nvKRixuoNB-p7"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Github integration",
			"rawText": "Github integration",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 261,
			"versionNonce": 274462984,
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
			"width": 163.17982482910156,
			"height": 25,
			"seed": 1626139009,
			"groupIds": [
				"Q0FVD_OoaGGNzngbRZeA-"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Markdown parser",
			"rawText": "Markdown parser",
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
					"id": "iFZdsrwbOm5PcKyt1p6GZ",
					"type": "arrow"
				}
			],
			"updated": 1710890430951,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 101,
			"versionNonce": 776076152,
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
			"width": 57.0599365234375,
			"height": 25,
			"seed": 1660698561,
			"groupIds": [
				"Bvd1F6J8nLeG4a7lKETIR"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Cards",
			"rawText": "Cards",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 173.056396484375,
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
			"rawText": "Insert tasks in board",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "QFDCOWaMb31cKyZTLljQX",
			"originalText": "Insert tasks in board",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 371,
			"versionNonce": 1267177480,
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
			"width": 256.77972412109375,
			"height": 25,
			"seed": 546385633,
			"groupIds": [
				"a-w8SDyMNdqaeQKINHORt"
			],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094730,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Saving user data in JSON",
			"rawText": "Saving user data in JSON",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 77.32815551757812,
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
			"rawText": "save/load",
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 47.40809631347656,
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
			"rawText": "issues",
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 137.6483154296875,
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
			"rawText": "tasks in calendar",
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 78.3681640625,
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
			"rawText": "Save/load",
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 85.52017211914062,
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
			"rawText": "issues/PRs",
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 199.20045471191406,
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
			"rawText": "sprints/time limited cards",
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 176.9763641357422,
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
			"rawText": "save/load (maybe ical)",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "cmGKrY0yp0tOKCZ1J9RPh",
			"originalText": "save/load (maybe ical)",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 39,
			"versionNonce": 1147391096,
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
			"width": 69.2801513671875,
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
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Saveable",
			"rawText": "Saveable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Saveable",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 49,
			"versionNonce": 2112313096,
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
			"width": 69.2801513671875,
			"height": 20,
			"seed": 1977850381,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Saveable",
			"rawText": "Saveable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Saveable",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 50,
			"versionNonce": 941849976,
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
			"width": 69.2801513671875,
			"height": 20,
			"seed": 1135451565,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Saveable",
			"rawText": "Saveable",
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
			"version": 97,
			"versionNonce": 1811363336,
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
			"width": 228.60850524902344,
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
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 16,
			"fontFamily": 1,
			"text": "This interface defines saving\n and loading to/from json",
			"rawText": "This interface defines saving\n and loading to/from json",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "This interface defines saving\n and loading to/from json",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 40,
			"versionNonce": 1522863736,
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
			"width": 135.5363006591797,
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
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 16,
			"fontFamily": 1,
			"text": "KanbanInsertable",
			"rawText": "KanbanInsertable",
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
			"version": 94,
			"versionNonce": 1790129416,
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
			"width": 354.99273681640625,
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
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 16,
			"fontFamily": 1,
			"text": "This interface defines how a class instance \nshould be displayed in a kanban board",
			"rawText": "This interface defines how a class instance \nshould be displayed in a kanban board",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "This interface defines how a class instance \nshould be displayed in a kanban board",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 45,
			"versionNonce": 890011512,
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
			"width": 135.5363006591797,
			"height": 20,
			"seed": 1445594403,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "KanbanInsertable",
			"rawText": "KanbanInsertable",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "KanbanInsertable",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 171,
			"versionNonce": 1335317512,
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
			"width": 285.8086242675781,
			"height": 80,
			"seed": 1984553891,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094731,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "a) * Use library -> commonmark-java\nb) Write own parser\n\n",
			"rawText": "a) * Use library -> commonmark-java\nb) Write own parser\n\n",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "a) * Use library -> commonmark-java\nb) Write own parser\n\n",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 67,
			"versionNonce": 389927032,
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
			"width": 230.08053588867188,
			"height": 20,
			"seed": 1853644771,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "String [md] -> String [HTML]",
			"rawText": "String [md] -> String [HTML]",
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
			"version": 43,
			"versionNonce": 1621070600,
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
			"width": 31.968017578125,
			"height": 45,
			"seed": 435412707,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"fontSize": 36,
			"fontFamily": 1,
			"text": "!!!",
			"rawText": "!!!",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "!!!",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 90,
			"versionNonce": 754020622,
			"isDeleted": false,
			"id": "gVysBEDo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1222.5018812741073,
			"y": -59.907716849159584,
			"strokeColor": "#e03131",
			"backgroundColor": "#ffc9c9",
			"width": 375.39959716796875,
			"height": 25,
			"seed": 2109121837,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712572679205,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "For (de)serialization use library GSON",
			"rawText": "For (de)serialization use library GSON",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "For (de)serialization use library GSON",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 22,
			"versionNonce": 790414546,
			"isDeleted": false,
			"id": "DwnQiQqm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1719.2608594977214,
			"y": 172.8944717806953,
			"strokeColor": "#f08c00",
			"backgroundColor": "transparent",
			"width": 109.16824340820312,
			"height": 20,
			"seed": 1671272045,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712572601601,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "KanbanDisplay",
			"rawText": "KanbanDisplay",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "rcapocY12rFDrei6zBW88",
			"originalText": "KanbanDisplay",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 75,
			"versionNonce": 757808594,
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
			"updated": 1712572601600,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "KN1ii9Km6KJn71_W5txdj",
				"focus": 0.19215309175039832,
				"gap": 11.310412056400764
			},
			"endBinding": {
				"elementId": "rcapocY12rFDrei6zBW88",
				"gap": 8.939988424190688,
				"focus": -0.2177066090459306
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
					66.6148978070305,
					-880.2768394993475
				]
			]
		},
		{
			"type": "arrow",
			"version": 36,
			"versionNonce": 1012050,
			"isDeleted": false,
			"id": "AoWYZMydHx1rOfwyZUjNX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1539.4294314155409,
			"y": 317.1361264580688,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 135.93655841574673,
			"height": 90.82795491740143,
			"seed": 1978896387,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1712572601601,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "WxyvQButunSW0vKEg_EAH",
				"gap": 11.448767216283386,
				"focus": 0.37623786661947856
			},
			"endBinding": {
				"elementId": "rcapocY12rFDrei6zBW88",
				"gap": 14.670207898803937,
				"focus": 0.23804506304456907
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
					135.93655841574673,
					-90.82795491740143
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 175.4723663330078,
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
			"rawText": "Add boards as events",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "ktU-5SMc1c55UkWdlAH8N",
			"originalText": "Add boards as events",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 278,
			"versionNonce": 613440146,
			"isDeleted": false,
			"id": "D-mETqQc_4izbG65DrqAb",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 252.34923468695752,
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
			"updated": 1712572601601,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 235,
			"versionNonce": 1357143570,
			"isDeleted": false,
			"id": "LlobDVY9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 450.2708088708303,
			"y": 717.8892801678369,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 213.29974365234375,
			"height": 25,
			"seed": 1986288,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712572601601,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Workspace - directory",
			"rawText": "Workspace - directory",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "D-mETqQc_4izbG65DrqAb",
			"originalText": "Workspace - directory",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 334,
			"versionNonce": 1955777490,
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
			"updated": 1712572601602,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 337,
			"versionNonce": 1097563986,
			"isDeleted": false,
			"id": "pWo05RHu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 457.25504218207476,
			"y": 787.0321721879261,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 202.75979614257812,
			"height": 25,
			"seed": 1607818768,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712572601602,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "worskpace_name.json",
			"rawText": "worskpace_name.json",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "3pHRWBXycgbDhiMdE04H-",
			"originalText": "worskpace_name.json",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 176,
			"versionNonce": 1210780904,
			"isDeleted": false,
			"id": "ELwdJI29_p4c27pvl5nXE",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 565.7777102031406,
			"y": 850.389192974756,
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
			"updated": 1711995524297,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 95,
			"versionNonce": 1145565704,
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
			"width": 157.75982666015625,
			"height": 50,
			"seed": 1227266576,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Kanban board 1 \nboard.json",
			"rawText": "Kanban board 1 \nboard.json",
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
			"version": 92,
			"versionNonce": 2045645432,
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
			"width": 187.31982421875,
			"height": 50,
			"seed": 1147063824,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "List1 <Card1, 2...>\nList2 <Card6, 9...>",
			"rawText": "List1 <Card1, 2...>\nList2 <Card6, 9...>",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "List1 <Card1, 2...>\nList2 <Card6, 9...>",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 178,
			"versionNonce": 85743080,
			"isDeleted": false,
			"id": "IMOW5Z7hy_641BSRSZPE6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 272.4196107238972,
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
			"updated": 1711995524298,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 110,
			"versionNonce": 2065301624,
			"isDeleted": false,
			"id": "Hk1Mhhqz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 314.90558206786085,
			"y": 1033.0207719221248,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 173.0198211669922,
			"height": 75,
			"seed": 572700400,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340104504,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Kanban board 2...\n3...\n....",
			"rawText": "Kanban board 2...\n3...\n....",
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
			"version": 60,
			"versionNonce": 1464685266,
			"isDeleted": false,
			"id": "o53Ja8Wy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 657.8992028614832,
			"y": 986.4606215461847,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 112.89987182617188,
			"height": 25,
			"seed": 1699964944,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712572601602,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "analogicznie",
			"rawText": "analogicznie",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "ELwdJI29_p4c27pvl5nXE",
			"originalText": "analogicznie",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 110,
			"versionNonce": 510674514,
			"isDeleted": false,
			"id": "7bGVTXUd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 297.26264939075713,
			"y": 1184.1154572306418,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 218.6244659423828,
			"height": 20,
			"seed": 1652965027,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712572601603,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "Card1: title..., card2: title...",
			"rawText": "Card1: title..., card2: title...",
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
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
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
			"customData": {
				"legacyTextWrap": true
			},
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
			"width": 256.439697265625,
			"height": 25,
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
			"text": "Structure of a workspace",
			"rawText": "Structure of a workspace",
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
			"version": 105,
			"versionNonce": 276104056,
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
			"width": 160.6083526611328,
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
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 16,
			"fontFamily": 1,
			"text": "board_id - dir\n|_ title.board [json]\n|_ title.cards [json]\n",
			"rawText": "board_id - dir\n|_ title.board [json]\n|_ title.cards [json]\n",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "board_id - dir\n|_ title.board [json]\n|_ title.cards [json]\n",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 65,
			"versionNonce": 535367688,
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
			"width": 99.08821105957031,
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
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 16,
			"fontFamily": 1,
			"text": "list 1 {\n    - cardId\n    - cardId\n    ...\n}\nlist 2\n...",
			"rawText": "list 1 {\n    - cardId\n    - cardId\n    ...\n}\nlist 2\n...",
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
			"version": 22,
			"versionNonce": 2062931064,
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
			"width": 63.248138427734375,
			"height": 100,
			"seed": 841917763,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1712340094732,
			"link": null,
			"locked": false,
			"fontSize": 16,
			"fontFamily": 1,
			"text": "card1 {\n}\ncard2 {\n}\n",
			"rawText": "card1 {\n}\ncard2 {\n}\n",
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
			"version": 295,
			"versionNonce": 1640814712,
			"isDeleted": false,
			"id": "eNnYaZ1Z",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -111.69908280620109,
			"y": 644.2176511876861,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 234.55810546875,
			"height": 251.73621232568607,
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
			"updated": 1712340118206,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			},
			"fontSize": 19.07092517618834,
			"fontFamily": 3,
			"text": "app\n|_workspaces\n  |_workspace1\n  | |_workspace1.ical\n  | |_board1\n  |   |_board [json]\n  |   |_cards [json]\n  | |_board2\n  | |_...\n  |_workspace2\n  |_...",
			"rawText": "app\n|_workspaces\n  |_workspace1\n  | |_workspace1.ical\n  | |_board1\n  |   |_board [json]\n  |   |_cards [json]\n  | |_board2\n  | |_...\n  |_workspace2\n  |_...",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "app\n|_workspaces\n  |_workspace1\n  | |_workspace1.ical\n  | |_board1\n  |   |_board [json]\n  |   |_cards [json]\n  | |_board2\n  | |_...\n  |_workspace2\n  |_...",
			"lineHeight": 1.2
		},
		{
			"type": "arrow",
			"version": 284,
			"versionNonce": 222932488,
			"isDeleted": false,
			"id": "jZONdshMOEiz_Nh7ccBT5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 561.9589300139471,
			"y": -78.08724236866806,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 495.71104120100813,
			"height": 705.131406792475,
			"seed": 285742832,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1712340115990,
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
					-495.71104120100813,
					705.131406792475
				]
			]
		}
	],
	"appState": {
		"theme": "light",
		"viewBackgroundColor": "#ffffff",
		"currentItemStrokeColor": "#1e1e1e",
		"currentItemBackgroundColor": "transparent",
		"currentItemFillStyle": "solid",
		"currentItemStrokeWidth": 2,
		"currentItemStrokeStyle": "solid",
		"currentItemRoughness": 1,
		"currentItemOpacity": 100,
		"currentItemFontFamily": 1,
		"currentItemFontSize": 20,
		"currentItemTextAlign": "left",
		"currentItemStartArrowhead": null,
		"currentItemEndArrowhead": "arrow",
		"scrollX": -570.6757706639385,
		"scrollY": 338.0890012203427,
		"zoom": {
			"value": 0.8000000000000003
		},
		"currentItemRoundness": "round",
		"gridSize": null,
		"gridColor": {
			"Bold": "#C9C9C9FF",
			"Regular": "#EDEDEDFF"
		},
		"currentStrokeOptions": null,
		"previousGridSize": null,
		"frameRendering": {
			"enabled": true,
			"clip": true,
			"name": true,
			"outline": true
		}
	},
	"files": {}
}
```
%%