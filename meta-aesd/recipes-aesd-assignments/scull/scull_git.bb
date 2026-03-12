DESCRIPTION = "Scull: Simple Character Utility for Loading Localities"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

inherit module update-rc.d

INITSCRIPT_NAME = "scull-init"
INITSCRIPT_PARAMS = "defaults 98"

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-siva7699.git;protocol=https;branch=main"
SRC_URI += " file://scull-init"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "732eb91bf9b765d6d7267b568049e82f00a4460b"


S = "${WORKDIR}/git"


EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/scull EXTRA_CFLAGS=-I${S}/include"

do_install() {
    module_do_install
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/scull-init ${D}${sysconfdir}/init.d/scull-init
}

FILES:${PN} += "${sysconfdir}/init.d/scull-init"