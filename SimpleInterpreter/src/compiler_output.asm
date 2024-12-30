section .data
x dd 1
y dd 1
z dd 5
section .text
global _start
_start:
;enter_scope
; Allocating a rax for  x
mov rax, 5
; Updating the new value of x for their register
mov rax, 6
mov rbx, [y]
; Allocating rcx  register for  z
mov rcx, rbx
mov rbx, rax
; Updating the new value of x for their register
mov rax, 10
mov rbx, rcx
mov rbx, rax
;exit_scope
mov dword [x], rax
mov dword [y], rbx
mov dword [z], rcx
mov rax, 1
xor rbx, rbx
int 0x80
